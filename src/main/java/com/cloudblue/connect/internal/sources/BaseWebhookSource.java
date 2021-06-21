package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.internal.sources.connections.WebhookListener;

import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Error;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.annotation.execution.OnError;
import org.mule.runtime.extension.api.annotation.execution.OnSuccess;
import org.mule.runtime.extension.api.annotation.execution.OnTerminate;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.*;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;
import org.mule.runtime.http.api.server.HttpServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.mule.runtime.api.metadata.DataType.STRING;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

@EmitsResponse
@MediaType(value = ANY, strict = false)
public abstract class BaseWebhookSource<T, H> extends Source<T, H> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseWebhookSource.class);

    @Inject
    private TransformationService transformationService;

    @Parameter
    @Placement(order = 1)
    private String path;

    @Parameter
    @Placement(order = 2)
    private String productId;

    @Parameter
    @Placement(order = 3)
    private String jwtSecret;


    @Connection
    private ConnectionProvider<WebhookListener> listenerProvider;

    @Config
    private CBCWebhookConfig webhookConfig;

    private HttpServer server;
    private String webhookId;
    private WebhookSourceHelper webhookSourceHelper;

    protected abstract String getObjectClass();

    protected abstract String getWebhookType();

    @Override
    public void onStart(SourceCallback<T, H> sourceCallback)
            throws MuleException {

        String listenerPath = webhookConfig.getFullListenerPath(
                path, getObjectClass()
        );

        WebhookAuthProvider authProvider = WebhookAuthProvider.builder().token(jwtSecret).build();

        WebhookListener webhookListener = listenerProvider.connect();
        server = webhookListener.getHttpServer();
        CBCConnection cbcConnection = webhookListener.getCbcConnection();
        webhookSourceHelper = new WebhookSourceHelper(webhookConfig, webhookListener, cbcConnection, transformationService);

        webhookId = webhookSourceHelper.updateWebhookObject(
                productId, getObjectClass(), getWebhookType(), jwtSecret, path
        );

        server.addRequestHandler(listenerPath, (requestContext, responseCallback) -> {
            try {

                Result<T, H> result = transformResult(requestContext);

                WebhookResponseContext responseContext = new WebhookResponseContext();
                responseContext.setResponseCallback(responseCallback);

                String token = getToken(result);

                if (!authProvider.authenticate(token))
                    webhookSourceHelper.sendResponse(401, new TypedValue<>("Authentication failed.", STRING), responseCallback);
                else {
                    SourceCallbackContext context = sourceCallback.createContext();
                    context.addVariable("RESPONSE_CONTEXT", responseContext);
                    sourceCallback.handle(result, context);
                }

            } catch (Throwable e) {
                LOGGER.error("Error during processing request", e);
                throw new MuleRuntimeException(e);
            }
        });
    }

    protected abstract String getToken(Result<T, H> result) throws Throwable;

    protected abstract Result<T, H> transformResult(HttpRequestContext requestContext) throws Exception;

    @OnSuccess
    public void onSuccess(
            @Optional(defaultValue = "200") @Placement(tab = "Responses") Integer responseStatusCode,
            @Optional(defaultValue = "#[output application/json --- payload]")
            @Placement(tab = "Responses") TypedValue<Object> responseBody,
            SourceCallbackContext callbackContext
    ) {
        webhookSourceHelper.sendResponse(responseStatusCode, responseBody, callbackContext);
    }

    @OnError
    public void onError(
            @Optional(defaultValue = "500") @Placement(tab = "Error Responses") Integer errorResponseStatusCode,
            SourceCallbackContext callbackContext,
            Error error,
            SourceCompletionCallback completionCallback
    ) {
        try {
            webhookSourceHelper.sendResponse(
                    errorResponseStatusCode, new TypedValue<>(error.getDescription(), STRING), callbackContext);
        } catch (Exception t) {
            completionCallback.error(t);
        }
    }

    @OnTerminate
    public void onTerminate(SourceResult sourceResult) {
        // Do nothing on termination.
    }

    @Override
    public void onStop() {
        server.stop();
        webhookSourceHelper.disableWebhook(webhookId);
    }
}
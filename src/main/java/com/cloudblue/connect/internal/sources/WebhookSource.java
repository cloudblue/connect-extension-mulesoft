package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.models.CBCWebhookEvent;

import com.cloudblue.connect.api.models.enums.CBCWebhookEventType;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;


import com.cloudblue.connect.internal.listeners.MuleContextStopListener;

import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.notification.NotificationListenerRegistry;
import org.mule.runtime.core.api.MuleContext;
import org.mule.runtime.extension.api.annotation.execution.OnSuccess;
import org.mule.runtime.extension.api.annotation.execution.OnTerminate;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.Source;
import org.mule.runtime.extension.api.runtime.source.SourceCallback;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;

import org.mule.runtime.extension.api.runtime.source.SourceResult;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.RequestHandler;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;

import javax.inject.Inject;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

@EmitsResponse
@MediaType(value = ANY, strict = false)
public class WebhookSource extends Source<CBCWebhookEvent, WebhookRequestAttributes> {

    @Parameter
    @Placement(order = 1)
    private String path;

    @Parameter
    @Placement(order = 2)
    private CBCWebhookEventType webhookEventType;

    @Parameter
    @Placement(order = 3)
    private String productId;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB)
    @Summary("Comma separated list of methods. Leave empty to allow all.")
    @Example("GET, POST")
    private String allowedMethods;

    @Connection
    private ConnectionProvider<WebhookListener> listenerProvider;

    @Config
    private CBCWebhookConfig webhookConfig;

    @Inject
    private MuleContext muleContext;

    @Inject
    private NotificationListenerRegistry notificationListenerRegistry;

    private MuleContextStopListener muleContextStopListener;
    private HttpServer server;


    @Override
    public void onStart(SourceCallback<CBCWebhookEvent, WebhookRequestAttributes> sourceCallback)
            throws MuleException {
        String listenerPath = webhookConfig.getFullListenerPath(webhookEventType.toString());

        server = listenerProvider.connect().getHttpServer();
        server.addRequestHandler(listenerPath, new RequestHandler() {
            @Override
            public void handleRequest(HttpRequestContext requestContext, HttpResponseReadyCallback responseCallback) {
                SourceCallbackContext ctx = sourceCallback.createContext();

                try {

                    Result<CBCWebhookEvent, WebhookRequestAttributes> result = WebhookRequestToResult
                            .transform(requestContext);

                    WebhookResponseContext responseContext = new WebhookResponseContext();
                    responseContext.setResponseCallback(responseCallback);

                    SourceCallbackContext context = sourceCallback.createContext();
                    context.addVariable("RESPONSE_CONTEXT", responseContext);
                    sourceCallback.handle(result, context);


                } catch (Exception e) {
                    throw new MuleRuntimeException(e);
                }
            }
        });
    }

    @OnSuccess
    public void onSuccess(
            @Optional(defaultValue = "200") @Placement(tab = "Responses") Integer responseStatusCode,
            SourceCallbackContext callbackContext
    ) throws Exception {

        WebhookResponseContext responseContext = callbackContext.<WebhookResponseContext>getVariable("RESPONSE_CONTEXT")
                .orElseThrow(() -> new MuleRuntimeException(
                        createStaticMessage("Response Context is not present. Could not send response.")
                ));

        final HttpResponseReadyCallback responseCallback = responseContext.getResponseCallback();
        HttpResponse response = HttpResponse.builder().statusCode(responseStatusCode).build();
        responseCallback.responseReady(
                response,
                new ResponseStatusCallback() {
                    @Override
                    public void responseSendFailure(Throwable exception) {}

                    @Override
                    public void responseSendSuccessfully() {}
                });
    }

    @OnTerminate
    public void onTerminate(SourceResult sourceResult) {}


    @Override
    public void onStop() {
        server.stop();
    }
}

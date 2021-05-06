package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCWebhook;
import com.cloudblue.connect.api.models.CBCWebhookEvent;
import com.cloudblue.connect.api.models.enums.CBCWebhookEventType;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.internal.sources.connections.WebhookListener;

import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Error;
import org.mule.runtime.extension.api.annotation.execution.OnError;
import org.mule.runtime.extension.api.annotation.execution.OnSuccess;
import org.mule.runtime.extension.api.annotation.execution.OnTerminate;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.*;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.entity.multipart.HttpPart;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.message.response.HttpResponseBuilder;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;

@EmitsResponse
@MediaType(value = ANY, strict = false)
public class WebhookSource extends Source<CBCWebhookEvent, WebhookRequestAttributes> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookSource.class);

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
    @Placement(order = 4)
    private String authenticationToken;

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

    private WebhookListener webhookListener;
    private HttpServer server;
    private CBCConnection cbcConnection;
    private String webhookId;

    public void updateWebhookObject() throws MuleRuntimeException {
        try {
            String listenerPath = webhookListener.getServerEndpoint() + webhookConfig.getFullListenerPath(
                    path, webhookEventType.toString().toLowerCase()
            );

            List<CBCWebhook> webhooks = (List<CBCWebhook>) cbcConnection
                    .newQ(new TypeReference<List<CBCWebhook>>() {})
                    .collection(NOTIFICATIONS)
                    .collection(WEBHOOKS)
                    .get();

            CBCWebhook webhook = null;
            if (webhooks != null && !webhooks.isEmpty()) {

                for (CBCWebhook w : webhooks) {
                    if (w.getProduct().getId().equals(productId) &&
                            w.getExternalUrl().equals(listenerPath) &&
                            w.getJwtToken().equals(authenticationToken) &&
                            w.getObjectClass() == webhookEventType) {
                        webhook = w;
                        break;
                    }
                }
            }

            if (webhook == null) {

                webhook = new CBCWebhook();
                webhook.setDescription("Webhook for Mule Extension Source " + listenerPath);
                webhook.setLabel("Webhook for Mule Extension Source");
                webhook.setProductId(productId);
                webhook.setActive(Boolean.TRUE);
                webhook.setExternalUrl(listenerPath);
                webhook.setJwtToken(authenticationToken);
                webhook.setObjectClass(webhookEventType);

                webhook = (CBCWebhook) cbcConnection.newQ(new TypeReference<CBCWebhook>() {})
                        .collection(NOTIFICATIONS)
                        .collection(WEBHOOKS)
                        .create(webhook);

            } else if (Boolean.FALSE.equals(webhook.getActive())) {
                CBCWebhook updateWebhook = new CBCWebhook();
                updateWebhook.setActive(Boolean.TRUE);

                cbcConnection.newQ(new TypeReference<CBCWebhook>() {})
                        .collection(NOTIFICATIONS)
                        .collection(WEBHOOKS, webhook.getId())
                        .update(updateWebhook);

            }

            webhookId = webhook.getId();
        } catch (CBCException e) {
            throw new MuleRuntimeException(e);
        }
    }

    public void disableWebhook() throws MuleRuntimeException {
        try {
            if (webhookId != null) {
                CBCWebhook updateWebhook = new CBCWebhook();
                updateWebhook.setActive(Boolean.FALSE);

                cbcConnection.newQ(new TypeReference<CBCWebhook>() {})
                        .collection(NOTIFICATIONS)
                        .collection(WEBHOOKS, webhookId)
                        .update(updateWebhook);
            }
        } catch (CBCException e) {
            throw new MuleRuntimeException(e);
        }
    }

    @Override
    public void onStart(SourceCallback<CBCWebhookEvent, WebhookRequestAttributes> sourceCallback)
            throws MuleException {

        String listenerPath = webhookConfig.getFullListenerPath(
                path, webhookEventType.toString().toLowerCase()
        );

        WebhookAuthProvider authProvider = WebhookAuthProvider.builder().token(authenticationToken).build();

        webhookListener = listenerProvider.connect();
        server = webhookListener.getHttpServer();
        cbcConnection = webhookListener.getCbcConnection();

        updateWebhookObject();

        server.addRequestHandler(listenerPath, (requestContext, responseCallback) -> {
            try {

                Result<CBCWebhookEvent, WebhookRequestAttributes> result = WebhookRequestToResult
                        .transform(requestContext);

                WebhookResponseContext responseContext = new WebhookResponseContext();
                responseContext.setResponseCallback(responseCallback);

                String token = result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                        createStaticMessage("Webhook Request Attributes are not found.")
                )).getToken();

                if (!authProvider.authenticate(token))
                    sendResponse(401, "Authentication failed.", responseCallback);
                else {
                    SourceCallbackContext context = sourceCallback.createContext();
                    context.addVariable("RESPONSE_CONTEXT", responseContext);
                    sourceCallback.handle(result, context);
                }

            } catch (Exception e) {
                throw new MuleRuntimeException(e);
            }
        });
    }

    @OnSuccess
    public void onSuccess(
            @Optional(defaultValue = "200") @Placement(tab = "Responses") Integer responseStatusCode,
            SourceCallbackContext callbackContext
    ) {
        sendResponse(responseStatusCode, null, callbackContext);
    }

    @OnError
    public void onError(
            @Optional(defaultValue = "500") @Placement(tab = "Error Responses") Integer errorResponseStatusCode,
            SourceCallbackContext callbackContext,
            Error error,
            SourceCompletionCallback completionCallback
    ) {
        try {
            sendResponse(errorResponseStatusCode, error.getDescription(), callbackContext);
        } catch (Exception t) {
            completionCallback.error(t);
        }
    }


    private void sendResponse(
            Integer responseCode,
            String responseBody,
            HttpResponseReadyCallback responseCallback
    ) {

        HttpResponseBuilder responseBuilder = HttpResponse.builder().statusCode(responseCode);

        if (responseBody != null && !responseBody.isEmpty()) {
            HttpEntity entity = buildEntity(responseBody);
            responseBuilder = responseBuilder.entity(entity);
        }

        responseCallback.responseReady(
                responseBuilder.build(),
                new ResponseStatusCallback() {
                    @Override
                    public void responseSendFailure(Throwable exception) {
                        // Do nothing on response send failure.
                    }

                    @Override
                    public void responseSendSuccessfully() {
                        // Do nothing on response send success.
                    }
                });
    }

    private void sendResponse(Integer responseCode, String responseBody, SourceCallbackContext callbackContext) {

        WebhookResponseContext responseContext = callbackContext.<WebhookResponseContext>getVariable("RESPONSE_CONTEXT")
                .orElseThrow(() -> new MuleRuntimeException(
                        createStaticMessage("Response Context is not present. Could not send response.")
                ));

        final HttpResponseReadyCallback responseCallback = responseContext.getResponseCallback();

        sendResponse(responseCode, responseBody, responseCallback);
    }

    private HttpEntity buildEntity(String body) {
        return new HttpEntity() {
            @Override
            public boolean isStreaming() {
                return false;
            }

            @Override
            public boolean isComposed() {
                return false;
            }

            @Override
            public InputStream getContent() {
                return new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public byte[] getBytes() throws IOException {
                return body.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Collection<HttpPart> getParts() throws IOException {
                return new ArrayList<>();
            }

            @Override
            public java.util.Optional<Long> getLength() {
                return java.util.Optional.of((long) body.length());
            }
        };
    }

    @OnTerminate
    public void onTerminate(SourceResult sourceResult) {
        // Do nothing on termination.
    }

    @Override
    public void onStop() {
        server.stop();
        disableWebhook();
    }
}

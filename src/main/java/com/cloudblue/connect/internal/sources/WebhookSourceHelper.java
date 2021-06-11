package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCWebhook;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.internal.sources.connections.WebhookListener;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Message;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.EmptyHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.entity.multipart.HttpPart;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.message.response.HttpResponseBuilder;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.NOTIFICATIONS;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.WEBHOOKS;
import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.api.metadata.DataType.BYTE_ARRAY;

public class WebhookSourceHelper {
    private final CBCWebhookConfig webhookConfig;
    private final WebhookListener webhookListener;
    private final CBCConnection cbcConnection;
    private final TransformationService transformationService;

    public WebhookSourceHelper(
            CBCWebhookConfig webhookConfig,
            WebhookListener webhookListener,
            CBCConnection cbcConnection,
            TransformationService transformationService) {
        this.webhookConfig = webhookConfig;
        this.webhookListener = webhookListener;
        this.cbcConnection = cbcConnection;
        this.transformationService = transformationService;
    }

    public String updateWebhookObject(
            String productId, String objectClass, String webhookType, String jwtSecret, String path
    ) throws MuleRuntimeException {
        try {
            String listenerPath = webhookListener.getServerEndpoint() + webhookConfig.getFullListenerPath(
                    path, objectClass
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
                            w.getJwtSecret().equals(jwtSecret) &&
                            w.getObjectClass().equals(objectClass) &&
                            w.getType().equals(webhookType)) {
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
                webhook.setJwtSecret(jwtSecret);
                webhook.setObjectClass(objectClass);
                webhook.setType(webhookType);

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

            return webhook.getId();
        } catch (CBCException e) {
            throw new MuleRuntimeException(e);
        }
    }

    public void disableWebhook(String webhookId) throws MuleRuntimeException {
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

    public void sendResponse(Integer responseCode, TypedValue<Object> responseBody, SourceCallbackContext callbackContext) {

        WebhookResponseContext responseContext = callbackContext.<WebhookResponseContext>getVariable("RESPONSE_CONTEXT")
                .orElseThrow(() -> new MuleRuntimeException(
                        createStaticMessage("Response Context is not present. Could not send response.")
                ));

        final HttpResponseReadyCallback responseCallback = responseContext.getResponseCallback();

        sendResponse(responseCode, responseBody, responseCallback);
    }

    private byte[] getMessageAsBytes(TypedValue payload) {
        return (byte[]) transformationService.transform(Message.builder().payload(payload).build(), BYTE_ARRAY).getPayload()
                .getValue();
    }

    public void sendResponse(
            Integer responseCode,
            TypedValue<Object> responseBody,
            HttpResponseReadyCallback responseCallback
    ) {

        HttpResponseBuilder responseBuilder = HttpResponse.builder().statusCode(responseCode);

        HttpEntity httpEntity;

        Object payload = responseBody.getValue();

        if (payload != null) {
            httpEntity = new ByteArrayHttpEntity(getMessageAsBytes(responseBody));
        } else {
            httpEntity = new EmptyHttpEntity();
        }

        responseBuilder = responseBuilder.entity(httpEntity);

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

}

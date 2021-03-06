/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.internal.config.CBCWebhookConfig;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.connection.WebhookConnection;
import com.cloudblue.connect.internal.error.exception.WebhookException;
import com.cloudblue.connect.internal.model.Webhook;
import com.cloudblue.connect.internal.model.resource.Action;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Message;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.EmptyHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.message.response.HttpResponseBuilder;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.NOTIFICATIONS;
import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.WEBHOOKS;
import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.api.metadata.DataType.BYTE_ARRAY;

public class WebhookSourceHelper {
    private final CBCWebhookConfig webhookConfig;
    private final WebhookConnection webhookConnection;
    private final CBCConnection connection;
    private final TransformationService transformationService;
    private final JacksonResponseUnmarshaller unmarshaller = new JacksonResponseUnmarshaller();

    private static final Logger logger = LoggerFactory.getLogger(WebhookSourceHelper.class);

    public WebhookSourceHelper(
            CBCWebhookConfig webhookConfig,
            WebhookConnection webhookConnection,
            CBCConnection connection,
            TransformationService transformationService) {
        this.webhookConfig = webhookConfig;
        this.webhookConnection = webhookConnection;
        this.connection = connection;
        this.transformationService = transformationService;
    }

    private Webhook identifyWebhook(String productId,
                                    String objectClass,
                                    String webhookType,
                                    String jwtSecret,
                                    String path,
                                    List<Webhook> webhooks) {
        Webhook webhook = null;
        for (Webhook w : webhooks) {
            if (w.getProduct().getId().equals(productId) &&
                    w.getExternalUrl().equals(path) &&
                    w.getJwtSecret().equals(jwtSecret) &&
                    w.getObjectClass().equals(objectClass) &&
                    w.getType().equals(webhookType)) {
                webhook = w;
                break;
            }
        }
        return webhook;
    }

    private Webhook webhookAction(Action action, Webhook input) {
        Result<InputStream, CBCResponseAttributes> result;

        CBCConnection.Query query = connection.newQuery().collection(NOTIFICATIONS);

        if (action == Action.CREATE) {
            result = query.collection(WEBHOOKS).create(input);
        } else {
            result = query.collection(WEBHOOKS, input.getId()).update(input);
        }

        ModuleException moduleException = new WebhookException("Not able to create/update Webhook Object");

        CBCResponseAttributes attributes = result.getAttributes()
                .orElseThrow(() -> moduleException);

        if (attributes.getStatusCode() != 200 && attributes.getStatusCode() != 201) {
            throw moduleException;
        }

        InputStream resultObject = result.getOutput();

        try {
            return unmarshaller.getObjectMapper().readValue(resultObject, Webhook.class);
        } catch (IOException e) {
            logger.error("Error during unmarshalling of json data", e);
            throw new WebhookException("Error during unmarshalling of json data");
        }
    }

    private Webhook updateOrCreateWebhook(String productId, String objectClass,
                                          String webhookType, String jwtSecret,
                                          String path, Webhook webhook) {
        Webhook updatedWebhook = null;

        if (webhook == null) {

            Webhook newWebhook = new Webhook();
            newWebhook.setDescription("Webhook for Mule Extension Source " + path);
            newWebhook.setLabel("Webhook for Mule Extension Source");
            newWebhook.setProductId(productId);
            newWebhook.setActive(Boolean.TRUE);
            newWebhook.setExternalUrl(path);
            newWebhook.setJwtSecret(jwtSecret);
            newWebhook.setObjectClass(objectClass);
            newWebhook.setType(webhookType);

            updatedWebhook = webhookAction(Action.CREATE, newWebhook);

        } else if (Boolean.FALSE.equals(webhook.getActive())) {
            Webhook updateWebhook = new Webhook();
            updateWebhook.setId(webhook.getId());
            updateWebhook.setActive(Boolean.TRUE);

            updatedWebhook = webhookAction(Action.UPDATE, updateWebhook);

        }

        return updatedWebhook;
    }

    public String updateWebhookObject(
            String productId, String objectClass,
            String webhookType, String jwtSecret,
            String path) {

        try {
            String listenerPath = webhookConnection.getServerEndpoint() + webhookConfig.getFullListenerPath(
                    path, objectClass
            );

            Result<InputStream, CBCResponseAttributes> result = connection.newQuery()
                    .collection(NOTIFICATIONS)
                    .collection(WEBHOOKS)
                    .get();

            ModuleException moduleException = new WebhookException("Not able to list existing Webhook Objects");

            CBCResponseAttributes attributes = result.getAttributes()
                    .orElseThrow(() -> moduleException);

            if (attributes.getStatusCode() != 200) {
                throw moduleException;
            }

            InputStream resultObject = result.getOutput();

            List<Webhook> webhooks = unmarshaller.getObjectMapper()
                    .readValue(resultObject, new TypeReference<List<Webhook>>() {});

            Webhook webhook = null;
            if (webhooks != null && !webhooks.isEmpty()) {

                webhook = identifyWebhook(productId, objectClass, webhookType,
                        jwtSecret, listenerPath, webhooks);
            }

            webhook = updateOrCreateWebhook(
                    productId, objectClass, webhookType,
                    jwtSecret, listenerPath, webhook
            );

            return webhook != null? webhook.getId() : null;
        } catch (IOException e) {
            throw new MuleRuntimeException(createStaticMessage("Error during updating webhook object"), e);
        }
    }

    public void disableWebhook(String webhookId) {
        if (webhookId != null) {
            Webhook updateWebhook = new Webhook();
            updateWebhook.setId(webhookId);
            updateWebhook.setActive(Boolean.FALSE);

            webhookAction(Action.UPDATE, updateWebhook);
        }
    }

    public void sendResponse(Integer responseCode, TypedValue<Object> responseBody, SourceCallbackContext callbackContext) {

        WebhookResponseContext responseContext = callbackContext.<WebhookResponseContext>getVariable("RESPONSE_CONTEXT")
                .orElseThrow(() -> new WebhookException("Response Context is not present. Could not send response."));

        final HttpResponseReadyCallback responseCallback = responseContext.getResponseCallback();

        sendResponse(responseCode, responseBody, responseCallback);
    }

    private byte[] getMessageAsBytes(TypedValue<Object> payload) {
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

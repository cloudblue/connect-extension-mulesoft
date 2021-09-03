/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.internal.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.api.parameters.filters.CBCResponseAttributes;
import com.cloudblue.connect.internal.model.Webhook;
import com.cloudblue.connect.internal.config.CBCWebhookConfig;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.connection.WebhookListener;
import com.cloudblue.connect.internal.model.resource.Action;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.api.exception.DefaultMuleException;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Message;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.EmptyHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.message.response.HttpResponseBuilder;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.cloudblue.connect.internal.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.api.metadata.DataType.BYTE_ARRAY;

public class WebhookSourceHelper {
    private final CBCWebhookConfig webhookConfig;
    private final WebhookListener webhookListener;
    private final CBCConnection connection;
    private final TransformationService transformationService;
    private final JacksonResponseUnmarshaller unmarshaller = new JacksonResponseUnmarshaller();

    public WebhookSourceHelper(
            CBCWebhookConfig webhookConfig,
            WebhookListener webhookListener,
            CBCConnection connection,
            TransformationService transformationService) {
        this.webhookConfig = webhookConfig;
        this.webhookListener = webhookListener;
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

    private Webhook webhookAction(Action action, Webhook input) throws MuleException {
        Result<InputStream, CBCResponseAttributes> result;

        CBCConnection.Q q = connection.newQ().collection(NOTIFICATIONS);

        if (action == Action.CREATE) {
            result = q.collection(WEBHOOKS).create(input);
        } else {
            result = q.collection(WEBHOOKS, input.getId()).update(input);
        }

        MuleException muleException = new DefaultMuleException("Not able to list existing Webhook Objects");

        CBCResponseAttributes attributes = result.getAttributes()
                .orElseThrow(() -> muleException);

        if (attributes.getStatusCode() != 200) {
            throw muleException;
        }

        InputStream resultObject = result.getOutput();

        try {
            return unmarshaller.getObjectMapper().readValue(resultObject, Webhook.class);
        } catch (IOException e) {
            throw muleException;
        }
    }

    private Webhook updateOrCreateWebhook(String productId, String objectClass,
                                          String webhookType, String jwtSecret,
                                          String path, Webhook webhook) throws MuleException {
        Webhook updatedWebhook = null;

        if (webhook == null) {

            webhook = new Webhook();
            webhook.setDescription("Webhook for Mule Extension Source " + path);
            webhook.setLabel("Webhook for Mule Extension Source");
            webhook.setProductId(productId);
            webhook.setActive(Boolean.TRUE);
            webhook.setExternalUrl(path);
            webhook.setJwtSecret(jwtSecret);
            webhook.setObjectClass(objectClass);
            webhook.setType(webhookType);

            updatedWebhook = webhookAction(Action.CREATE, webhook);

        } else if (Boolean.FALSE.equals(webhook.getActive())) {
            Webhook updateWebhook = new Webhook();
            updateWebhook.setActive(Boolean.TRUE);

            updatedWebhook = webhookAction(Action.UPDATE, updateWebhook);

        }

        return updatedWebhook;
    }

    public String updateWebhookObject(
            String productId, String objectClass, String webhookType, String jwtSecret, String path
    ) throws MuleException {
        try {
            String listenerPath = webhookListener.getServerEndpoint() + webhookConfig.getFullListenerPath(
                    path, objectClass
            );

            Result<InputStream, CBCResponseAttributes> result = connection.newQ()
                    .collection(NOTIFICATIONS)
                    .collection(WEBHOOKS)
                    .get();

            MuleException muleException = new DefaultMuleException("Not able to list existing Webhook Objects");

            CBCResponseAttributes attributes = result.getAttributes()
                    .orElseThrow(() -> muleException);

            if (attributes.getStatusCode() != 200) {
                throw muleException;
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
            throw new DefaultMuleException(e);
        }
    }

    public void disableWebhook(String webhookId) throws MuleException {
        if (webhookId != null) {
            Webhook updateWebhook = new Webhook();
            updateWebhook.setActive(Boolean.FALSE);

            connection.newQ()
                    .collection(NOTIFICATIONS)
                    .collection(WEBHOOKS, webhookId)
                    .update(updateWebhook);
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

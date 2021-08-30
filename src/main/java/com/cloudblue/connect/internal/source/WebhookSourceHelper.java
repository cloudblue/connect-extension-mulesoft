/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.internal.config.CBCWebhookConfig;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.connection.WebhookListener;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.message.Message;
import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.transformation.TransformationService;
import org.mule.runtime.extension.api.runtime.source.SourceCallbackContext;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.EmptyHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.http.api.domain.message.response.HttpResponseBuilder;
import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;
import org.mule.runtime.http.api.server.async.ResponseStatusCallback;

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

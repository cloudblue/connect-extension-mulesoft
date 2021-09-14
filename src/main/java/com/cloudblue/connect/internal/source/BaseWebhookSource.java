/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.internal.config.CBCWebhookConfig;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.connection.WebhookListener;

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
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.source.*;
import org.mule.runtime.http.api.server.HttpServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.mule.runtime.api.metadata.DataType.STRING;

public abstract class BaseWebhookSource<T, H> extends Source<T, H> {

    private static final Logger logger = LoggerFactory.getLogger(BaseWebhookSource.class);

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
                Result<T, H> result = (Result<T, H>) RequestToResult.transform(requestContext);

                WebhookResponseContext responseContext = new WebhookResponseContext();
                responseContext.setResponseCallback(responseCallback);
                String token = BaseWebhookSource.this.getToken(result);
                if (!authProvider.authenticate(token))
                    webhookSourceHelper.sendResponse(401, new TypedValue<>("Authentication failed.", STRING), responseCallback);
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

    protected abstract String getToken(Result<T, H> result) throws MuleRuntimeException;

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
        try {
            server.stop();
            webhookSourceHelper.disableWebhook(webhookId);
        } catch (MuleException e) {
            logger.error("Error during stopping services.", e);
        }
    }
}
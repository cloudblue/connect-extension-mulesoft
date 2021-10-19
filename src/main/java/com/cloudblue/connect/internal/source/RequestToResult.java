/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import com.cloudblue.connect.internal.clients.constants.HeaderParams;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import java.io.InputStream;


public class RequestToResult {

    private RequestToResult() {}

    public static Result<InputStream, WebhookRequestAttributes> transform(
            final HttpRequestContext requestContext) throws MuleRuntimeException {
        String authHeader;
        String token = null;
        final HttpRequest request = requestContext.getRequest();

        final HttpEntity entity = request.getEntity();
        InputStream inputStream = entity.getContent();

        MultiMap<String, String> headers = request.getHeaders();

        WebhookRequestAttributes attributes = new WebhookRequestAttributes();
        if (headers.containsKey(HeaderParams.AUTHENTICATION.toLowerCase())){
            authHeader = headers.get(HeaderParams.AUTHENTICATION.toLowerCase());
            if (authHeader != null && authHeader.contains("Bearer ")) {
                token = authHeader.split(" ")[1];
            }

            attributes.setToken(token);
        }

        Result.Builder<InputStream, WebhookRequestAttributes> resultBuilder = Result.builder();

        return resultBuilder.output(inputStream).mediaType(MediaType.APPLICATION_JSON).attributes(attributes).build();
    }
}

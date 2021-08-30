/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.api.models.enums.RequestValidationType;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;

public class RequestValidationSource extends BaseWebhookSource<CBCRequest, WebhookRequestAttributes> {

    @Parameter
    @Placement(order = 4)
    private RequestValidationType validationType;

    @Override
    protected String getObjectClass() {
        return "fulfillment_request";
    }

    @Override
    protected String getWebhookType() {
        return validationType.getType();
    }

    @Override
    protected String getToken(Result<CBCRequest, WebhookRequestAttributes> result) throws MuleRuntimeException {
        return result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                createStaticMessage("Webhook Request Attributes are not found.")
        )).getToken();
    }

    @Override
    protected Result<CBCRequest, WebhookRequestAttributes> transformResult(
            HttpRequestContext requestContext) throws MuleRuntimeException {
        return RequestToResult.transformRequest(requestContext);
    }
}

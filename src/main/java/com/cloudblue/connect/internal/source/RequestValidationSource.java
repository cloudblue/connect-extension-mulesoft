/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.api.webhook.RequestValidationType;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

@EmitsResponse
@MediaType(value = APPLICATION_JSON, strict = false)
@OutputJsonType(schema = MetadataUtil.FULFILLMENT_REQUEST_SCHEMA)
public class RequestValidationSource extends BaseWebhookSource<InputStream, WebhookRequestAttributes> {

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
    protected String getToken(Result<InputStream, WebhookRequestAttributes> result) throws MuleRuntimeException {
        return result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                createStaticMessage("Webhook Request Attributes are not found.")
        )).getToken();
    }
}

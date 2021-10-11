/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.source;

import com.cloudblue.connect.api.webhook.TCRValidationType;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import com.cloudblue.connect.internal.error.exception.WebhookException;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

@EmitsResponse
@MediaType(value = APPLICATION_JSON, strict = false)
@OutputJsonType(schema = MetadataUtil.TIER_CONFIG_REQUEST_SCHEMA)
@Alias("tcr-validation-listener")
public class TCRValidationSource extends BaseWebhookSource<InputStream, WebhookRequestAttributes> {

    @Parameter
    @Placement(order = 4)
    @DisplayName("Validation Type")
    private TCRValidationType validationType;

    @Override
    protected String getObjectClass() {
        return "tier_config_request";
    }

    @Override
    protected String getWebhookType() {
        return validationType.getType();
    }

    @Override
    protected String getToken(Result<InputStream, WebhookRequestAttributes> result) {
        return result.getAttributes().orElseThrow(() ->
                new WebhookException("Webhook Request Attributes are not found."))
                .getToken();
    }
}

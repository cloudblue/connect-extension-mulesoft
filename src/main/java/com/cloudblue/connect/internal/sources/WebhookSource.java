package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.models.CBCWebhookEvent;
import com.cloudblue.connect.api.models.enums.CBCWebhookEventType;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

@EmitsResponse
@MediaType(value = ANY, strict = false)
public class WebhookSource extends BaseWebhookSource<CBCWebhookEvent, WebhookRequestAttributes> {

    @Parameter
    @Placement(order = 4)
    private CBCWebhookEventType webhookEventType;

    @Override
    protected String getObjectClass() {
        return webhookEventType.name().toLowerCase();
    }

    @Override
    protected String getWebhookType() {
        return "event";
    }

    @Override
    protected String getToken(Result<CBCWebhookEvent, WebhookRequestAttributes> result) throws MuleRuntimeException {
        return result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                createStaticMessage("Webhook Request Attributes are not found.")
        )).getToken();
    }

    @Override
    protected Result<CBCWebhookEvent, WebhookRequestAttributes> transformResult(HttpRequestContext requestContext) throws MuleRuntimeException {
        return RequestToResult.transformWebhook(requestContext);
    }
}

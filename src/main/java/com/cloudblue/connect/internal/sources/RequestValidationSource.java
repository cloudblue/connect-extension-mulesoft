package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;

public class RequestValidationSource extends BaseWebhookSource<CBCRequest, WebhookRequestAttributes> {

    @Override
    protected String getObjectClass() {
        return "fulfillment_request";
    }

    @Override
    protected String getWebhookType() {
        return "validator";
    }

    @Override
    protected String getToken(Result<CBCRequest, WebhookRequestAttributes> result) throws Throwable {
        return result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                createStaticMessage("Webhook Request Attributes are not found.")
        )).getToken();
    }

    @Override
    protected Result<CBCRequest, WebhookRequestAttributes> transformResult(
            HttpRequestContext requestContext) throws Exception {
        return RequestToResult.transformRequest(requestContext);
    }
}

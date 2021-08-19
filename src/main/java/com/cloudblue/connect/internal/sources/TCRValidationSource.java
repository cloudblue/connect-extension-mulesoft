package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.models.enums.TCRValidationType;
import com.cloudblue.connect.api.models.tier.CBCTierConfigRequest;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;


public class TCRValidationSource extends BaseWebhookSource<CBCTierConfigRequest, WebhookRequestAttributes> {

    @Parameter
    @Placement(order = 4)
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
    protected String getToken(Result<CBCTierConfigRequest, WebhookRequestAttributes> result) throws MuleRuntimeException {
        return result.getAttributes().orElseThrow(() -> new MuleRuntimeException(
                createStaticMessage("Webhook Request Attributes are not found.")
        )).getToken();
    }

    @Override
    protected Result<CBCTierConfigRequest, WebhookRequestAttributes> transformResult(HttpRequestContext requestContext) throws MuleRuntimeException {
        return RequestToResult.transformTCR(requestContext);
    }
}

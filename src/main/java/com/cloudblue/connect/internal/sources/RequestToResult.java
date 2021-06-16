package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.clients.constants.HeaderParams;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.api.models.CBCWebhookEvent;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.models.tier.CBCTierConfigRequest;
import com.cloudblue.connect.api.webhook.WebhookRequestAttributes;
import org.apache.commons.io.IOUtils;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.request.HttpRequestContext;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class RequestToResult {

    private RequestToResult() {}

    public static <T> Result<T, WebhookRequestAttributes> transform(
            final HttpRequestContext requestContext, Class<T> clazz)
            throws Exception {
        String authHeader;
        String token = null;
        final HttpRequest request = requestContext.getRequest();

        final HttpEntity entity = request.getEntity();
        InputStream inputStream = entity.getContent();

        String payload = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        T object = new JacksonResponseUnmarshaller().unmarshal(payload, clazz);

        MultiMap<String, String> headers = request.getHeaders();

        WebhookRequestAttributes attributes = new WebhookRequestAttributes();
        if (headers.containsKey(HeaderParams.AUTHENTICATION.toLowerCase())){
            authHeader = headers.get(HeaderParams.AUTHENTICATION.toLowerCase());
            if (authHeader != null && authHeader.contains("Bearer ")) {
                token = authHeader.split(" ")[1];
            }

            attributes.setToken(token);
        }

        Result.Builder<T, WebhookRequestAttributes> resultBuilder = Result.builder();

        return resultBuilder.output(object).mediaType(MediaType.APPLICATION_JAVA).attributes(attributes).build();
    }

    public static Result<CBCWebhookEvent, WebhookRequestAttributes> transformWebhook(final HttpRequestContext requestContext)
            throws Exception {
        return transform(requestContext, CBCWebhookEvent.class);
    }

    public static Result<CBCRequest, WebhookRequestAttributes> transformRequest(final HttpRequestContext requestContext)
            throws Exception {
        return transform(requestContext, CBCRequest.class);
    }

    public static Result<CBCTierConfigRequest, WebhookRequestAttributes> transformTCR(final HttpRequestContext requestContext)
            throws Exception {
        return transform(requestContext, CBCTierConfigRequest.class);
    }
}

package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.api.clients.constants.HeaderParams;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.api.models.CBCWebhookEvent;
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


public class WebhookRequestToResult {
    private WebhookRequestToResult() {}

    public static Result<CBCWebhookEvent, WebhookRequestAttributes> transform(final HttpRequestContext requestContext)
            throws Exception {
        final HttpRequest request = requestContext.getRequest();

        final HttpEntity entity = request.getEntity();
        InputStream inputStream = entity.getContent();

        String payload = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

        CBCWebhookEvent event = new JacksonResponseUnmarshaller().unmarshal(payload, CBCWebhookEvent.class);

        MultiMap<String, String> headers = request.getHeaders();

        WebhookRequestAttributes attributes = new WebhookRequestAttributes();
        if (headers.containsKey(HeaderParams.AUTHORIZATION))
            attributes.setToken(headers.get(HeaderParams.AUTHORIZATION));

        Result.Builder<CBCWebhookEvent, WebhookRequestAttributes> resultBuilder = Result.builder();

        return resultBuilder.output(event).mediaType(MediaType.APPLICATION_JAVA).attributes(attributes).build();
    }
}

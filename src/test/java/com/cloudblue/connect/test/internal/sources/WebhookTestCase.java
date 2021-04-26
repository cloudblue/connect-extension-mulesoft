package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import org.mule.extension.http.api.HttpResponseAttributes;
import org.mule.runtime.api.event.Event;
import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.tck.junit4.rule.DynamicPort;

public class WebhookTestCase extends BaseMuleFlowTestCase {
    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public DynamicPort webhookPort = new DynamicPort("webhook_port");

    @Override
    protected String getConfigFile() {
        return "test-mule-config-webhook.xml";
    }

    @Test
    public void testWebhookSource() throws Exception {
        Event event = flowRunner("testWebhook").run();
        HttpResponseAttributes attributes = (HttpResponseAttributes) event.getMessage().getAttributes().getValue();

        Assert.assertEquals(200, attributes.getStatusCode());
    }

    @Test(expected = Exception.class)
    public void testWebhookSourceNegative() throws Exception {
        flowRunner("testWebhookError").run();
    }
}

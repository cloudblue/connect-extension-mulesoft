package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;
import org.junit.Rule;
import org.junit.Test;
import org.mule.runtime.api.event.Event;
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

    @Test(expected = Test.None.class)
    public void testWebhookSource() throws Exception {
        Event getRequest = flowRunner("testWebhook").run();
    }
}

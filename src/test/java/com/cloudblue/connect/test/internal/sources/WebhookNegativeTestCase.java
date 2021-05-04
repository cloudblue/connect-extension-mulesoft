package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mule.extension.http.api.HttpResponseAttributes;
import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

public class WebhookNegativeTestCase extends BaseMuleFlowTestCase {
    private static final String WEBHOOK_ID = "WB-0000-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public DynamicPort webhookPort = new DynamicPort("webhook_port");

    @Rule
    public SystemProperty webhookIdSystemProperty = new SystemProperty("webhook_id", WEBHOOK_ID);

    @Override
    protected String getConfigFile() {
        return "test-mule-config-webhook-negative.xml";
    }

    @Test(expected = Test.None.class)
    public void testWebhookSourceNegative() throws Exception {
        flowRunner("testWebhookError").run();
    }
}

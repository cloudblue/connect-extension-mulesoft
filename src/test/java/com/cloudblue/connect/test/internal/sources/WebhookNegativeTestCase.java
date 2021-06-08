package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import org.junit.Rule;
import org.junit.Test;

import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

public class WebhookNegativeTestCase extends BaseMuleFlowTestCase {
    private static final String WEBHOOK_ID = "WB-0000-0000-0000";
    private static final String secret = "Test-token-key-for-webhook-based-authentication";

    private static final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ3ZWJob29rX2lkIjoi" +
            "V0gtNjMyLTcyNS0xNjkiLCJleHAiOjQ3MjMwNTc0NzF9.4agSUe4nQtU957sK_YDoBn8HRVUlboFlOV4zcNmKWGg";

    @Rule
    public SystemProperty tokenSystemProperty = new SystemProperty("jwt_token", token);

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public DynamicPort webhookPort = new DynamicPort("webhook_port");

    @Rule
    public SystemProperty webhookIdSystemProperty = new SystemProperty("webhook_id", WEBHOOK_ID);

    @Rule
    public SystemProperty secretSystemProperty = new SystemProperty("jwt_secret", secret);

    @Override
    protected String getConfigFile() {
        return "test-mule-config-webhook-negative.xml";
    }

    @Test(expected = Test.None.class)
    public void testWebhookSourceNegative() throws Exception {
        flowRunner("testWebhookError").run();
    }
}

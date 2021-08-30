/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mule.extension.http.api.HttpResponseAttributes;
import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

public class TcrDynamicValidationTestCase extends BaseMuleFlowTestCase {
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
        return "test-mule-config-tcr-dynamic-validation.xml";
    }

    @Test
    public void testTCRDynamicValidationSource() throws Exception {
        Event event = flowRunner("testWebhook").run();
        HttpResponseAttributes attributes = (HttpResponseAttributes) event.getMessage().getAttributes().getValue();

        Assert.assertEquals(200, attributes.getStatusCode());
    }

}

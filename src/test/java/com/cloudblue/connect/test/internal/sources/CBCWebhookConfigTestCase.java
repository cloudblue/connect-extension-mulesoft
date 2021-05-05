package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.internal.sources.CBCWebhookConfig;
import org.junit.Assert;
import org.junit.Test;

public class CBCWebhookConfigTestCase {
    @Test
    public void testGetFullListenerPathSniffedEndWith() {
        CBCWebhookConfig config = new CBCWebhookConfig();
        config.setBasePath("/basepath/");

        String listenerPath = config.getFullListenerPath("/extra/", "webhook");

        Assert.assertEquals("/basepath/", config.getBasePath());
        Assert.assertEquals("/basepath/extra/webhook", listenerPath);
    }
}

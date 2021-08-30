/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.config.CBCWebhookConfig;
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

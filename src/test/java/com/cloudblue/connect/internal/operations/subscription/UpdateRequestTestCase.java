/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.subscription;

import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;
import org.junit.Rule;
import org.junit.Test;
import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UpdateRequestTestCase extends BaseMuleFlowTestCase {

    private static final String REQUEST_ID = "PR-0000-0000-0000-001";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty requestIdSystemProperty = new SystemProperty("request_id", REQUEST_ID);


    @Override
    protected String getConfigFile() {
        return "test-mule-config-update-request.xml";
    }

    @Test
    public void testUpdateRequest() throws Exception {
        Event getRequest = flowRunner("updateRequest").run();
        CBCRequest request = (CBCRequest)getRequest.getMessage().getPayload().getValue();
        assertThat(request.getId(), is(REQUEST_ID));
    }
}

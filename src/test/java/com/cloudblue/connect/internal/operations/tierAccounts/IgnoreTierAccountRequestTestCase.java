/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.tierAccounts;

import com.cloudblue.connect.api.models.tier.CBCAccountRequest;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.test.runner.RunnerDelegateTo;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunnerDelegateTo(Parameterized.class)
public class IgnoreTierAccountRequestTestCase extends BaseMuleFlowTestCase {

    private static final String TIERACCOUNTREQUEST_ID = "TAR-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty tierAccountRequestIdSystemProperty = new SystemProperty("tierAccountRequest_id", TIERACCOUNTREQUEST_ID);

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"ignoreTierAccountRequest"}
            }
        );
    }

    public IgnoreTierAccountRequestTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-ignore-tier-account-request.xml";
    }

    @Test
    public void testIgnoreTierAccountRequestCase() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        CBCAccountRequest request = (CBCAccountRequest)getRequest.getMessage().getPayload().getValue();
        assertThat(request.getId(), is(TIERACCOUNTREQUEST_ID));
    }
}

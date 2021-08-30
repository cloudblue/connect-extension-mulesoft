/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.models.usage.CBCUsageReconciliation;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.test.runner.RunnerDelegateTo;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunnerDelegateTo(Parameterized.class)
public class UsageReconTestCase extends BaseMuleFlowTestCase {

    private static final String USAGE_RECON_ID = "RCF-0000-00-0000-0000-001";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty usageReconIdSystemProperty = new SystemProperty("usage_recon_id", USAGE_RECON_ID);

    @Rule
    public SystemProperty filePath = new SystemProperty(
            "filePath",
            this.getClass().getClassLoader()
                    .getResource("Recon_File.xlsx").getPath()
    );

    @Rule
    public SystemProperty tempFilePath = new SystemProperty(
            "tempFilePath",
            System.getProperty("java.io.tmpdir")
    );

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"createUsageRecon"},
                {"downloadUsageReconFile"}
        });
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir") + File.separator + USAGE_RECON_ID + ".txt");
        if (file.exists())
            file.delete();
    }

    public UsageReconTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-usage-recon.xml";
    }

    @Test
    public void testUsageRecon() throws Exception {
        Event event = flowRunner(this.flow).run();
        CBCUsageReconciliation usageReconciliation  = (CBCUsageReconciliation)event
                .getMessage().getPayload().getValue();
        assertThat(usageReconciliation.getId(), is(USAGE_RECON_ID));
    }
}

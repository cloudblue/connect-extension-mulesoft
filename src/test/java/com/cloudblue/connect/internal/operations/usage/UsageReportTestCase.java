/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.models.usage.CBCUsageReport;
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
public class UsageReportTestCase extends BaseMuleFlowTestCase {

    private static final String USAGE_REPORT_ID = "UF-0000-00-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty usageReportIdSystemProperty = new SystemProperty("usage_report_id", USAGE_REPORT_ID);

    @Rule
    public SystemProperty filePath = new SystemProperty(
            "filePath",
            this.getClass().getClassLoader()
                    .getResource("Usage_File.xlsx").getPath()
    );

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"createUsageReport"},
                {"updateUsageReport"},
                {"submitUsageReport"},
                {"acceptUsageReport"},
                {"uploadUsageReport"}
        });
    }

    public UsageReportTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-usage-report.xml";
    }

    @Test
    public void testUsageReport() throws Exception {
        Event event = flowRunner(this.flow).run();
        CBCUsageReport usageReport = (CBCUsageReport)event.getMessage().getPayload().getValue();
        assertThat(usageReport.getId(), is(USAGE_REPORT_ID));
    }
}

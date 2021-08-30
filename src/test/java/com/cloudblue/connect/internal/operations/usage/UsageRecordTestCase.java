/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.internal.common.BaseMuleFlowTestCase;

import org.junit.Rule;
import org.junit.Test;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UsageRecordTestCase extends BaseMuleFlowTestCase {

    private static final String USAGE_RECORD_ID = "UR-0000-00-0000-0000-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty usageRecordIdSystemProperty = new SystemProperty("usage_record_id", USAGE_RECORD_ID);

    @Override
    protected String getConfigFile() {
        return "test-mule-config-usage-record.xml";
    }

    @Test
    public void testUpdateUsageRecord() throws Exception {
        Event event = flowRunner("updateUsageRecords").run();
        CBCUsageRecord usageRecord = (CBCUsageRecord)event.getMessage().getPayload().getValue();
        assertThat(usageRecord.getId(), is(USAGE_RECORD_ID));
    }

    @Test
    public void testBulkCloseUsageRecord() throws Exception {
        Event event = flowRunner("closeUsageRecords").run();
        List<CBCUsageRecord> usageRecord = (List<CBCUsageRecord>)event.getMessage().getPayload().getValue();
        assertThat(usageRecord.get(0).getId(), is(USAGE_RECORD_ID));
    }
}

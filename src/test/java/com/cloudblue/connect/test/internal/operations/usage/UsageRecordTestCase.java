package com.cloudblue.connect.test.internal.operations.usage;

import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

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
public class UsageRecordTestCase extends BaseMuleFlowTestCase {

    private static final String USAGE_RECORD_ID = "UR-0000-00-0000-0000-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty usageRecordIdSystemProperty = new SystemProperty("usage_record_id", USAGE_RECORD_ID);

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"closeUsageRecords"},
                {"updateUsageRecords"}
        });
    }

    public UsageRecordTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-usage-record.xml";
    }

    @Test
    public void testUsageRecord() throws Exception {
        Event event = flowRunner(this.flow).run();
        CBCUsageRecord usageRecord = (CBCUsageRecord)event.getMessage().getPayload().getValue();
        assertThat(usageRecord.getId(), is(USAGE_RECORD_ID));
    }
}

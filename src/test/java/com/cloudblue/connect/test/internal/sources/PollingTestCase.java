package com.cloudblue.connect.test.internal.sources;

import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import org.junit.Rule;
import org.junit.Test;

import org.mule.tck.junit4.rule.DynamicPort;


public class PollingTestCase extends BaseMuleFlowTestCase {

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Override
    protected String getConfigFile() {
        return "test-mule-config-polling-positive.xml";
    }

    @Test(expected = Test.None.class)
    public void testPollingSource() throws Exception {
        Thread.sleep(5000);
    }
}

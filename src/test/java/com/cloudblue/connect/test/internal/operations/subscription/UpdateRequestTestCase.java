package com.cloudblue.connect.test.internal.operations.subscription;

import com.cloudblue.connect.api.models.CBCRequest;
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

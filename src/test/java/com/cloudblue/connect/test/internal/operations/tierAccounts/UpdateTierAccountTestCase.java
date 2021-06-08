package com.cloudblue.connect.test.internal.operations.tierAccounts;

import com.cloudblue.connect.api.models.tier.CBCAccount;
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
public class UpdateTierAccountTestCase extends BaseMuleFlowTestCase {

    private static final String TIERACCOUNT_ID = "TA-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty tierAccountIdSystemProperty = new SystemProperty("tierAccount_id", TIERACCOUNT_ID);

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"updateTierAccount"}
            }
        );
    }

    public UpdateTierAccountTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-update-tier-account.xml";
    }

    @Test
    public void testApproveTierConfigRequestCase() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        CBCAccount request = (CBCAccount)getRequest.getMessage().getPayload().getValue();
        assertThat(request.getId(), is(TIERACCOUNT_ID));
    }
}

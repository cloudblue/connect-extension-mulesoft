package com.cloudblue.connect.test.internal.operations.subscription;

import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;

import org.junit.runners.Parameterized;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;
import org.mule.test.runner.RunnerDelegateTo;

@RunnerDelegateTo(Parameterized.class)
public class GetOperationsTestCase extends BaseMuleFlowTestCase {
    
    private static final String REQUEST_ID = "PR-0000-0000-0000-001";
    private static final String ASSERT_ID = "AS-0000-0000-0000";
    
    @Rule
    public DynamicPort listenPort = new DynamicPort("port");
    
    @Rule
    public SystemProperty requestIdSystemProperty = new SystemProperty("request_id", REQUEST_ID);
    
    @Rule
    public SystemProperty assetIdSystemProperty = new SystemProperty("asset_id", ASSERT_ID);
    
    private final String flow;
    private final Class clazz;
    private final String expectedIdValue;
    
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                    {"getRequest", CBCRequest.class, REQUEST_ID},
                    {"getAsset", CBCAsset.class, ASSERT_ID},
                    {"listRequests", null, null},
                    {"listAssets", null, null}
                }
        );
    }

    public GetOperationsTestCase(String flow, Class clazz, String expectedIdValue) {
        this.flow = flow;
        this.clazz = clazz;
        this.expectedIdValue = expectedIdValue;
    }
    
    
    @Test
    public void testGetRequest() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        Object object = getRequest.getMessage().getPayload().getValue();

        if (this.clazz != null) {
            Method idMethod = this.clazz.getDeclaredMethod("getId");
            String idValue = (String)idMethod.invoke(object);

            assertThat(idValue, is(this.expectedIdValue));
        } else {
            assertTrue(object instanceof List);
            assertEquals(((List)object).size(), 1);
        }

    }
}

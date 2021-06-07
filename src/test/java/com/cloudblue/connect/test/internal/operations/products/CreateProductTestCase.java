package com.cloudblue.connect.test.internal.operations.products;

import com.cloudblue.connect.api.models.product.CBCProduct;
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
public class CreateProductTestCase extends BaseMuleFlowTestCase {

    private static final String PRODUCT_ID = "PRD-0000-0000";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty productIdSystemProperty = new SystemProperty("product_id", PRODUCT_ID);

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"createProduct"}
            }
        );
    }

    public CreateProductTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-create-product.xml";
    }

    @Test
    public void testCreateHelpdeskCase() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        CBCProduct request = (CBCProduct)getRequest.getMessage().getPayload().getValue();
        assertThat(request.getId(), is(PRODUCT_ID));
    }
}

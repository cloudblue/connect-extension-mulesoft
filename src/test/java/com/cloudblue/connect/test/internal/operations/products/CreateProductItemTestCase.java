package com.cloudblue.connect.test.internal.operations.products;

import com.cloudblue.connect.api.models.product.CBCProductItem;
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
public class CreateProductItemTestCase extends BaseMuleFlowTestCase {

    private static final String PRODUCTITEM_ID = "PRD-0000-0000-001";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty productItemIdSystemProperty = new SystemProperty("productItem_id", PRODUCTITEM_ID);

    private final String flow;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"createProductItem"}
            }
        );
    }

    public CreateProductItemTestCase(String flow) {
        this.flow = flow;
    }

    @Override
    protected String getConfigFile() {
        return "test-mule-config-create-product-item.xml";
    }

    @Test
    public void testCreateHelpdeskCase() throws Exception {
        Event getRequest = flowRunner(this.flow).run();
        CBCProductItem request = (CBCProductItem)getRequest.getMessage().getPayload().getValue();
        assertThat(request.getId(), is(PRODUCTITEM_ID));
    }
}

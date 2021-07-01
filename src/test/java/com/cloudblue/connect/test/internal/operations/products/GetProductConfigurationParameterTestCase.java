package com.cloudblue.connect.test.internal.operations.products;

import com.cloudblue.connect.api.models.product.CBCProductConfigurationParameter;
import com.cloudblue.connect.test.internal.common.BaseMuleFlowTestCase;

import org.junit.Rule;
import org.junit.Test;

import org.mule.runtime.api.event.Event;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.tck.junit4.rule.SystemProperty;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetProductConfigurationParameterTestCase extends BaseMuleFlowTestCase {

    private static final String PRODUCT_ID = "PR-0000-0000";

    private static final String VALUE = "12345";

    @Rule
    public DynamicPort listenPort = new DynamicPort("port");

    @Rule
    public SystemProperty productIdSystemProperty = new SystemProperty("product_id", PRODUCT_ID);

    @Rule
    public SystemProperty valueSystemProperty = new SystemProperty("value", VALUE);


    @Override
    protected String getConfigFile() {
        return "test-mule-config-get-product-configuration-parameter.xml";
    }

    @Test
    public void testGetProductConfigurationParameterCase() throws Exception {
        Event event = flowRunner("listProductConfigurationParameter").run();
        List<CBCProductConfigurationParameter> records = (List<CBCProductConfigurationParameter>)event.getMessage().getPayload().getValue();
        assertThat(records.get(0).getValue(), is(VALUE));
    }    
}

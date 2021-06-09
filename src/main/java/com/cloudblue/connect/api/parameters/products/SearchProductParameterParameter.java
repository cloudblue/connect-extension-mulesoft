package com.cloudblue.connect.api.parameters.products;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SearchProductParameterParameter {
    @Parameter
    @Expression
    private String productId;

    @Parameter
    @Expression
    private String parameterId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }    
}

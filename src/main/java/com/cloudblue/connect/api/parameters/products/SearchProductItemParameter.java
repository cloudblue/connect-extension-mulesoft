package com.cloudblue.connect.api.parameters.products;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class SearchProductItemParameter {
    @Parameter
    @Expression
    private String productId;

    @Parameter
    @Expression
    private String productItemId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductItemId() {
        return productItemId;
    }

    public void setProductItemId(String productItemId) {
        this.productItemId = productItemId;
    }    
}

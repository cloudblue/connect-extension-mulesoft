package com.cloudblue.connect.api.parameters.products;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class GetProductActionParameter {
    @Parameter
    @Expression
    private String productId;

    @Parameter
    @Expression
    private String actionId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }
}

package com.cloudblue.connect.api.parameters.products;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class GetProductTemplateParameter {
    @Parameter
    @Expression
    private String productId;

    @Parameter
    @Expression
    private String templateId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}

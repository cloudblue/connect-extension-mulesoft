package com.cloudblue.connect.api.parameters.products;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class GetProductActionLinkParameter extends GetProductActionParameter {
    @Parameter
    @Expression
    private String assetId;

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
}

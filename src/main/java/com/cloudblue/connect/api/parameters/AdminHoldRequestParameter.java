package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.models.enums.AdminHoldRequestType;
import com.cloudblue.connect.api.models.enums.CBCRequestType;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class AdminHoldRequestParameter implements Embeddable {

    @Parameter
    private AdminHoldRequestType requestType;

    @Parameter
    private String assetId;

    public AdminHoldRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(AdminHoldRequestType requestType) {
        this.requestType = requestType;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    @Override
    public Object buildEntity() {
        CBCRequest request = new CBCRequest();
        request.setType(CBCRequestType.valueOf(this.requestType.toString()));

        request.setAsset(new CBCAsset());
        request.getAsset().setId(this.assetId);

        return request;
    }

}

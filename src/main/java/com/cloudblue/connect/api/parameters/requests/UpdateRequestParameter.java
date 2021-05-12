package com.cloudblue.connect.api.parameters.requests;

import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.models.CBCRequestParam;
import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.List;

public class UpdateRequestParameter extends ResourceActionParameter implements Embeddable {

    @Parameter
    private List<CBCRequestParam> params;

    public List<CBCRequestParam> getParams() {
        return params;
    }

    public void setParams(List<CBCRequestParam> params) {
        this.params = params;
    }

    @Override
    public Object buildEntity() {
        CBCRequest request = new CBCRequest();

        request.setAsset(new CBCAsset());
        request.getAsset().setParams(this.params);

        return request;
    }
}

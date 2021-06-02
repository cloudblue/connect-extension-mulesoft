package com.cloudblue.connect.api.parameters.accounts;

import java.util.List;
import java.util.ArrayList;

import com.cloudblue.connect.api.models.subscription.CBCRequestParam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class UpdateTierConfigRequestParameter {

    @Parameter
    @DisplayName("Tier Config Request ID")
    private String tierConfigRequestId;

    @Parameter
    @Optional
    @DisplayName("Parameter ID")
    private String parameterId;

    @Parameter
    @Optional
    @DisplayName("Parameter Value")
    private String parameterValue;

    public String getTierConfigRequestId() {
        return tierConfigRequestId;
    }

    public void setTierConfigRequestId(String tierConfigRequestId) {
        this.tierConfigRequestId = tierConfigRequestId;
    }

    public String getParameterId() {
        return parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    @JsonIgnore
    public Object buildEntity() {

        CBCRequestParam param = new CBCRequestParam();
        param.setId(this.parameterId);
        param.setValue(this.parameterValue);

        List<CBCRequestParam> params = new ArrayList<>();
        params.add(param);

        return params;
    }
}

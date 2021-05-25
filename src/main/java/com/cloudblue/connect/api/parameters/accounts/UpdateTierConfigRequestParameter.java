package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.CBCParams;
import java.util.List;
import java.util.ArrayList;

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
        List<CBCParams> recipients = new ArrayList<CBCParams>();
        CBCParams element = new CBCParams();
        recipients.add(element);
        element.setId(this.parameterId);
        element.setValue(this.parameterValue);       
        return recipients;
    }
}

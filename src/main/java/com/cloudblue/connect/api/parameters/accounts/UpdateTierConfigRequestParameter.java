package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.CBCParams;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.List;

public class UpdateTierConfigRequestParameter {

    @Parameter
    @DisplayName("Tier Config Request ID")
    private String tierConfigRequestId;

    @Parameter
    @Optional
    private List<CBCParams> params;

    public String getTierConfigRequestId() {
        return tierConfigRequestId;
    }

    public void setTierConfigRequestId(String tierConfigRequestId) {
        this.tierConfigRequestId = tierConfigRequestId;
    }

    public List<CBCParams> getParams() {
        return params;
    }

    public void setParams(List<CBCParams> params) {
        this.params = params;
    }
}

package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.CBCTemplate;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class ActionTierConfigRequestParameter {

    @Parameter
    @DisplayName("Tier Config Request ID")
    private String tierConfigRequestId;

    public String getTierConfigRequestId() {
        return tierConfigRequestId;
    }

    public void setTierConfigRequestId(String tierConfigRequestId) {
        this.tierConfigRequestId = tierConfigRequestId;
    }
}

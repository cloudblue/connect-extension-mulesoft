package com.cloudblue.connect.api.parameters.accounts;

import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class AcceptTierAccountRequestParameter {

    @Parameter
    @DisplayName("Tier Account Request ID")
    private String tierAccountRequestId;

    public String getTierAccountRequestId() {
        return tierAccountRequestId;
    }

    public void setTierAccountRequestId(String tierAccountRequestId) {
        this.tierAccountRequestId = tierAccountRequestId;
    }

}

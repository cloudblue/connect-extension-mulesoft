package com.cloudblue.connect.api.parameters.accounts;

import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class IgnoreTierAccountRequestParameter {

    @Parameter
    @DisplayName("Tier Account Request ID")
    private String tierAccountRequestId;

    @Parameter
    private String reason;

    public String getTierAccountRequestId() {
        return tierAccountRequestId;
    }

    public void setTierAccountRequestId(String tierAccountRequestId) {
        this.tierAccountRequestId = tierAccountRequestId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}

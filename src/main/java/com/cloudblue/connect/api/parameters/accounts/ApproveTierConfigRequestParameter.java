package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.CBCTemplate;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class ApproveTierConfigRequestParameter {

    @Parameter
    @DisplayName("Tier Config Request ID")
    private String tierConfigRequestId;

    @Parameter
    @Optional
    private String templateId;

    public String getTierConfigRequestId() {
        return tierConfigRequestId;
    }

    public void setTierConfigRequestId(String tierConfigRequestId) {
        this.tierConfigRequestId = tierConfigRequestId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Object buildEntity() {
        CBCTemplate payload = new CBCTemplate();
        payload.setId(this.templateId);
        return payload;
    }
}

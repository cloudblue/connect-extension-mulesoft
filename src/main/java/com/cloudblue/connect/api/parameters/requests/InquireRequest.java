package com.cloudblue.connect.api.parameters.requests;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class InquireRequest extends RequestAction {
    @Parameter
    @Expression
    @DisplayName("Template ID")
    @Placement(order = 2)
    private String templateId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String action() {
        return "inquire";
    }

    @Override
    public Object buildEntity() {
        addValue("template_id", templateId);

        return payload;
    }
}

package com.cloudblue.connect.api.parameters.requests;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class RejectRequest extends RequestAction {
    @Parameter
    @Expression
    @Placement(order = 2)
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String action() {
        return "fail";
    }

    @Override
    public Object buildEntity() {
        addValue("reason", reason);

        return payload;
    }
}

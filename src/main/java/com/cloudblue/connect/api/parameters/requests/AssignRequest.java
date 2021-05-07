package com.cloudblue.connect.api.parameters.requests;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class AssignRequest extends RequestAction {

    @Parameter
    @Expression
    @Placement(order = 2)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String action() {
        return "assign";
    }

    @Override
    public Object buildEntity() {
        addValue("email", email);

        return payload;
    }
}

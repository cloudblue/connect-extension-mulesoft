package com.cloudblue.connect.api.parameters.requests;

public class UnassignRequest extends RequestAction {
    @Override
    public String action() {
        return "unassign";
    }

    @Override
    public Object buildEntity() {
        return null;
    }
}

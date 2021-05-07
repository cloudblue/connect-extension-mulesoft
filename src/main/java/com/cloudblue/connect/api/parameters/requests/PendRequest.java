package com.cloudblue.connect.api.parameters.requests;

public class PendRequest extends RequestAction {
    @Override
    public String action() {
        return "pend";
    }

    @Override
    public Object buildEntity() {
        return null;
    }
}

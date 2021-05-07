package com.cloudblue.connect.api.parameters.requests;

public class PurchaseRequest extends RequestAction {
    @Override
    public String action() {
        return "purchase";
    }

    @Override
    public Object buildEntity() {
        return null;
    }
}

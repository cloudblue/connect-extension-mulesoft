package com.cloudblue.connect.api.clients.constants;

public enum ContentType {
    JSON("application/json, application/*+json"), URL_CODED_FORM("application/x-www-form-urlencoded");
    private String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

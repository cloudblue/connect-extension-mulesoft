package com.cloudblue.connect.api.models.enums;

public enum RequestValidationType {
    DRAFT_VALIDATION("validator"),
    INQUIRING_VALIDATION("inquiring_validator"),
    CHANGE_VALIDATION("change_validator");

    private String type;

    RequestValidationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

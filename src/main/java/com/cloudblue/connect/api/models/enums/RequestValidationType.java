package com.cloudblue.connect.api.models.enums;

public enum RequestValidationType {
    Draft_Validator("validator"),
    Inquiring_Validator("inquiring_validator"),
    Change_Validator("change_validator");

    private String type;

    RequestValidationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

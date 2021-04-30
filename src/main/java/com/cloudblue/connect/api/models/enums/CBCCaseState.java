package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
public enum CBCCaseState {
    @JsonProperty("pending")
    PENDING("pend"), 
    @JsonProperty("inquiring")
    INQUIRING("inquire"), 
    @JsonProperty("resolved")
    RESOLVED("resolve"),
    @JsonProperty("closed")
    CLOSED("close");

    private String operation;
 
    CBCCaseState(String operation) {
        this.operation = operation;
    }
 
    public String getOperation() {
        return operation;
    }
}


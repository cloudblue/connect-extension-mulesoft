package com.cloudblue.connect.api.models.enums;

public enum CBCCaseState {
    pending("pend"), 
    inquiring("inquire"), 
    resolved("resolve"), 
    closed("close");

    private String operation;
 
    CBCCaseState(String operation) {
        this.operation = operation;
    }
 
    public String getOperation() {
        return operation;
    }
}


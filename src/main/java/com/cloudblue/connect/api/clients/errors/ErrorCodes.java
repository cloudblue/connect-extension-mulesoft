package com.cloudblue.connect.api.clients.errors;

public enum ErrorCodes {
    CONNECT_COMMUNICATION_ERROR("connect communication error"),
    CONNECTAPI_ERROR ("connect-api-error"),
    EXTENSION_ERROR ("connect-extension-error"),
    INTERNAL_ERROR("internal-error"),
    CONFIGURATION_ERROR ("configuration-error"),
    HTTP_CLIENT_ERROR ("http-client-initiate-error");
    
    private final String value;

    ErrorCodes(String s) {
        value = s;
    }
    public String getValue() {
        return value;
    }

}

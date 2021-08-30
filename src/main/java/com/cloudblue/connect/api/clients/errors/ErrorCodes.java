/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

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

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.webhook;

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

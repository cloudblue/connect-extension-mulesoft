/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductParameterType {
    @JsonProperty("text")
    TEXT,

    @JsonProperty("dropdown")
    DROPDOWN,

    @JsonProperty("password")
    PASSWORD,

    @JsonProperty("email")
    EMAIL,

    @JsonProperty("checkbox")
    CHECKBOX,

    @JsonProperty("subdomain")
    SUBDOMAIN,

    @JsonProperty("domain")
    DOMAIN,

    @JsonProperty("phone")
    PHONE,

    @JsonProperty("url")
    URL,

    @JsonProperty("choice")
    CHOICE
}

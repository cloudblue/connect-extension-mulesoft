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

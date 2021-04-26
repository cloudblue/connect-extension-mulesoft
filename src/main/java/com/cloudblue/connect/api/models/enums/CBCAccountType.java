package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCAccountType {
    @JsonProperty("vendor")
    VENDOR,

    @JsonProperty("provider")
    PROVIDER
}

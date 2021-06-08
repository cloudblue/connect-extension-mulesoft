package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductItemPrecision {
    @JsonProperty("integer")
    INTEGER,

    @JsonProperty("decimal(1)")
    DOUBLE_1,

    @JsonProperty("decimal(2)")
    DOUBLE_2,

    @JsonProperty("decimal(4)")
    DOUBLE_4,

    @JsonProperty("decimal(8)")
    DOUBLE_8,
}

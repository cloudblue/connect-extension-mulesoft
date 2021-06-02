package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductItemPrecision {
    @JsonProperty("integer")
    INTEGER,

    @JsonProperty("double")
    DOUBLE
}

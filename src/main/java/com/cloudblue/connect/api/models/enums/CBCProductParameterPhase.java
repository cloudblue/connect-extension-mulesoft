package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductParameterPhase {
    @JsonProperty("configuration")
    CONFIGURATION,

    @JsonProperty("fulfillment")
    FULFILLMENT,

    @JsonProperty("ordering")
    ORDERING
}

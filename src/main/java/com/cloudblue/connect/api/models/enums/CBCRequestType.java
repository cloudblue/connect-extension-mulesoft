package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCRequestType {
    @JsonProperty("purchase")
    PURCHASE,

    @JsonProperty("change")
    CHANGE,

    @JsonProperty("suspend")
    SUSPEND,

    @JsonProperty("resume")
    RESUME,

    @JsonProperty("cancel")
    CANCEL,

    @JsonProperty("adjustment")
    ADJUSTMENT
}

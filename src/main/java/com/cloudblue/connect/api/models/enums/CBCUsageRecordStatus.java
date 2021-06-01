package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCUsageRecordStatus {
    @JsonProperty("valid")
    VALID,

    @JsonProperty("invalid")
    INVALID,

    @JsonProperty("approved")
    APPROVED,

    @JsonProperty("rejected")
    REJECTED,

    @JsonProperty("closed")
    CLOSED;
}

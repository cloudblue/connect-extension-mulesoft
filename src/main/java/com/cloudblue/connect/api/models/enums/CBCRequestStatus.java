package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCRequestStatus {
    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("pending")
    PENDING,

    @JsonProperty("inquiring")
    INQUIRING,

    @JsonProperty("failed")
    FAILED,

    @JsonProperty("approved")
    APPROVED
}

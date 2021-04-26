package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCAssetStatus {
    @JsonProperty("active")
    ACTIVE,

    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("processing")
    PROCESSING,

    @JsonProperty("rejected")
    REJECTED,

    @JsonProperty("terminating")
    TERMINATING,

    @JsonProperty("suspended")
    SUSPENDED,

    @JsonProperty("terminated")
    TERMINATED
}

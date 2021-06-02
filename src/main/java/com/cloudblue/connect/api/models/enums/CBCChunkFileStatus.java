package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCChunkFileStatus {
    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("ready")
    READY,

    @JsonProperty("closed")
    CLOSED,

    @JsonProperty("failed")
    FAILED;
}

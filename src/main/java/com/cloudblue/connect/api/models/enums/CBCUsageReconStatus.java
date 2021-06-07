package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCUsageReconStatus {
    @JsonProperty("uploaded")
    UPLOADED,

    @JsonProperty("processing")
    PROCESSING,

    @JsonProperty("processed")
    PROCESSED,

    @JsonProperty("failed")
    FAILED;
}

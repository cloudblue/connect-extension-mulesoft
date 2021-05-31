package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCUsageReportStatus {
    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("uploading")
    UPLOADING,

    @JsonProperty("uploaded")
    UPLOADED,

    @JsonProperty("processing")
    PROCESSING,

    @JsonProperty("invalid")
    INVALID,

    @JsonProperty("ready")
    READY,

    @JsonProperty("rejected")
    REJECTED,

    @JsonProperty("pending")
    PENDING,

    @JsonProperty("accepted")
    ACCEPTED,

    @JsonProperty("closed")
    CLOSED,

    @JsonProperty("deleted")
    DELETED;
}

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCExtensionStatus {
    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("success")
    SUCCESS,

    @JsonProperty("published")
    PUBLISHED,

    @JsonProperty("unrestricted")
    UNRESTRICTED
}

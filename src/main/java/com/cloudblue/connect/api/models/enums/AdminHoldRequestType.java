package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AdminHoldRequestType {
    @JsonProperty("suspend")
    SUSPEND,

    @JsonProperty("resume")
    RESUME,

    @JsonProperty("cancel")
    CANCEL
}

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCTemplateType {
    @JsonProperty("inquire")
    INQUIRE,

    @JsonProperty("fulfillment")
    FULFILLMENT;
}

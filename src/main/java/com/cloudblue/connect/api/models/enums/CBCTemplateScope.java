package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCTemplateScope {
    @JsonProperty("asset")
    ASSET,

    @JsonProperty("tier1")
    TIER1,

    @JsonProperty("tier2")
    TIER2;
}

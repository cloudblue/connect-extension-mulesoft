package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCUsageReportSchema {
    @JsonProperty("qt")
    QT,

    @JsonProperty("tr")
    TR,

    @JsonProperty("pr")
    PR,

    @JsonProperty("cr")
    CR;
}

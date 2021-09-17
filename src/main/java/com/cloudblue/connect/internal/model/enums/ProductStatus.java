package com.cloudblue.connect.internal.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductStatus {
    @JsonProperty("draft")
    DRAFT,

    @JsonProperty("published")
    PUBLISHED,

    @JsonProperty("endofsale")
    END_OF_SALE
}

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductParameterScope {
    @JsonProperty("asset")
    ASSET,

    @JsonProperty("tier1")
    TIER1,

    @JsonProperty("tier2")
    TIER2,

    @JsonProperty("product")
    PRODUCT,

    @JsonProperty("item")
    ITEM,

    @JsonProperty("marketplace")
    MARKETPLACE,

    @JsonProperty("item_marketplace")
    ITEM_MARKETPLACE
}

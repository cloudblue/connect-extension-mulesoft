package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductItemType {
    @JsonProperty("reservation")
    RESERVATION,

    @JsonProperty("ppu")
    PPU
}

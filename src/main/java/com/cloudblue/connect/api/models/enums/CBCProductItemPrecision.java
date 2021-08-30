/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCProductItemPrecision {
    @JsonProperty("integer")
    INTEGER,

    @JsonProperty("decimal(1)")
    DOUBLE_1,

    @JsonProperty("decimal(2)")
    DOUBLE_2,

    @JsonProperty("decimal(4)")
    DOUBLE_4,

    @JsonProperty("decimal(8)")
    DOUBLE_8,
}

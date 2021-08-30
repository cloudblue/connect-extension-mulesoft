/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.subscription;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCRequestItem implements CBCEntity {
    @Parameter
    @JsonProperty
    private String id;
    
    @Parameter
    @JsonProperty
    private Integer quantity;

    @JsonProperty("old_quantity")
    private Integer oldQuantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        if (quantity!=null) {
            if (quantity instanceof String && ((String) quantity).equalsIgnoreCase("unlimited")) {
                this.quantity = -1;
            } else {
                this.quantity = Integer.parseInt(quantity.toString());
            }
        }
    }

    public Integer getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(Object oldQuantity) {
        if (oldQuantity!=null) {
            if (oldQuantity instanceof String && ((String) oldQuantity).equalsIgnoreCase("unlimited")) {
                this.oldQuantity = -1;
            } else {
                this.oldQuantity = Integer.parseInt(oldQuantity.toString());
            }
        }
    }
}

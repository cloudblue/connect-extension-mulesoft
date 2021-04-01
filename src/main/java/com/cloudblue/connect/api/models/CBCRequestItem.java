package com.cloudblue.connect.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCRequestItem {
    @Parameter
    private String id;
    
    @Parameter
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

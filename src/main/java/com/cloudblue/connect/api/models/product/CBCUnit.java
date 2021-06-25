package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUnit extends CBCCommonEntity {

    @JsonProperty
    private String title;

    @JsonProperty
    private String unit;

    public CBCUnit(String unit) {
        this.unit = unit;
    }

    public CBCUnit() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }    

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }    
}

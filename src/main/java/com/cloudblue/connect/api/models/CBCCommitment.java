package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCommitment implements CBCEntity {

    @JsonProperty
    private String multiplier;

    @JsonProperty
    private String count;
    
    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }    

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }    

}

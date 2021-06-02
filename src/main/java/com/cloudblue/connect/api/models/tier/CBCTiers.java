package com.cloudblue.connect.api.models.tier;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCTiers implements CBCEntity {

    @JsonProperty
    private CBCAccount tier1;

    @JsonProperty
    private CBCAccount tier2;   

    public CBCAccount getTier1() {
        return tier1;
    }

    public void setTier1(CBCAccount tier1) {
        this.tier1 = tier1;
    }  

    public CBCAccount getTier2() {
        return tier2;
    }

    public void setTier2(CBCAccount tier2) {
        this.tier2 = tier2;
    }  
}

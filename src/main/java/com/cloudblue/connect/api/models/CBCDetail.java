package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCDetail implements CBCEntity {

    @JsonProperty
    private String at;

    @JsonProperty
    private CBCBy by;   

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }    

    public CBCBy getBy() {
        return by;
    }

    public void setBy(CBCBy by) {
        this.by = by;
    }  
}

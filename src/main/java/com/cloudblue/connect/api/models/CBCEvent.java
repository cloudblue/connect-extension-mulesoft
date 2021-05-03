package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCEvent implements CBCEntity {

    @JsonProperty
    private String at;

    @JsonProperty
    private CBCUser by;   

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }    

    public CBCUser getBy() {
        return by;
    }

    public void setBy(CBCUser by) {
        this.by = by;
    }  
}

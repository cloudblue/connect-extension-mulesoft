package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUser implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String externalId;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
 
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }    


}

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCapabilitiesPpu implements CBCEntity {

    @JsonProperty
    private String schema;

    @JsonProperty
    private Boolean dynamic;

    @JsonProperty
    private Boolean predictive;
    
    @JsonProperty
    private Boolean future;
    
    public String getSchena() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }    

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }    

    public Boolean getPredictive() {
        return predictive;
    }

    public void setPredictive(Boolean predictive) {
        this.predictive = predictive;
    }    

    public Boolean getFuture() {
        return future;
    }

    public void setFuture(Boolean future) {
        this.future = future;
    }    
}
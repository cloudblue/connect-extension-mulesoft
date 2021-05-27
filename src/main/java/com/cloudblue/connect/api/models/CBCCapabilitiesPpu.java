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
    private String dynamic;

    @JsonProperty
    private String predictive;
    
    @JsonProperty
    private String future;
    
    public String getSchena() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }    

    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }    

    public String getPredictive() {
        return predictive;
    }

    public void setPredictive(String predictive) {
        this.predictive = predictive;
    }    

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }    
}

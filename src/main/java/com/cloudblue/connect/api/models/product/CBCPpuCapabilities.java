/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCPpuCapabilities implements CBCEntity {

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

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CBCRequestParamConstraints implements CBCEntity {

    @Parameter
    @JsonProperty
    private String required;

    @Parameter
    @JsonProperty
    public String hidden;

    @Parameter
    @JsonProperty
    public String unique;

    @Parameter
    @JsonProperty
    public String reconciliation;

    @Parameter
    @JsonProperty
    public String shared;

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }  

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }      
   
    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }      
   
    public String getReconciliation() {
        return reconciliation;
    }

    public void setReconciliation(String reconciliation) {
        this.reconciliation = reconciliation;
    }      
  
    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }      
    
}

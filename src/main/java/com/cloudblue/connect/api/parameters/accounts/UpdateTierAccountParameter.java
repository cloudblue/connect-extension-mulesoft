package com.cloudblue.connect.api.parameters.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class UpdateTierAccountParameter extends NewTierAccountParameter {

    @Parameter
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {
        return super.buildEntity();
    }
}

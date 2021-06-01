package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageRule implements CBCEntity {

    @JsonProperty
    private Boolean enabled;

    @JsonProperty
    private List<CBCCommonEntity> providers;
    
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }    

    public List<CBCCommonEntity> getProviders() {
        return providers;
    }

    public void setProviders(List<CBCCommonEntity> providers) {
        this.providers = providers;
    }    
}

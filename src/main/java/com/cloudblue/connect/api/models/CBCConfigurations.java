package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCConfigurations implements CBCEntity {
    @JsonProperty
    private Boolean suspendResumeSupported;
    
    @JsonProperty
    private Boolean requiresResellerInformation;

    public Boolean getSuspendResumeSupported() {
        return suspendResumeSupported;
    }

    public void setSuspendResumeSupported(Boolean suspendResumeSupported) {
        this.suspendResumeSupported = suspendResumeSupported;
    }

    public Boolean getRequiresResellerInformation() {
        return requiresResellerInformation;
    }

    public void setRequiresResellerInformation(Boolean requiresResellerInformation) {
        this.requiresResellerInformation = requiresResellerInformation;
    }
}

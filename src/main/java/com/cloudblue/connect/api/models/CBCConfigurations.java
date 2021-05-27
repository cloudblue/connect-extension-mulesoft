package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCConfigurations implements CBCEntity {
    @JsonProperty
    private String suspendResumeSupported;
    
    @JsonProperty
    private String requiresResellerInformation;

    public String getSuspendResumeSupported() {
        return suspendResumeSupported;
    }

    public void setSuspendResumeSupported(String suspendResumeSupported) {
        this.suspendResumeSupported = suspendResumeSupported;
    }

    public String getRequiresResellerInformation() {
        return requiresResellerInformation;
    }

    public void setRequiresResellerInformation(String requiresResellerInformation) {
        this.requiresResellerInformation = requiresResellerInformation;
    }
}

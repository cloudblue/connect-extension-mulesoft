package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.cloudblue.connect.api.models.enums.CBCExtensionStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCExtensions implements CBCEntity {
    @JsonProperty
    private String id;
    
    @JsonProperty
    private String icon;

    @JsonProperty
    private String name;

    @JsonProperty
    private CBCExtensionStatus status;


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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public CBCExtensionStatus getStatus() {
        return status;
    }

    public void setStatus(CBCExtensionStatus status) {
        this.status = status;
    }
    
}
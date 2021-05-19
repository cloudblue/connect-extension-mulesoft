package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCTierConfigRequest implements CBCEntity {
    @JsonProperty
    private String id;

    @JsonProperty
    private String type;

    @JsonProperty
    private String status;

    @JsonProperty
    private CBCConfiguration configuration;

    @JsonProperty
    private CBCEvents events;

    @JsonProperty
    private List<CBCRequestParam> params;

    @JsonProperty
    private CBCUser asignee;

    @JsonProperty
    private CBCUser template;

    @JsonProperty
    private String environment;

    @JsonProperty
    private CBCTiers tiers;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CBCConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(CBCConfiguration configuration) {
        this.configuration = configuration;
    }    
    
    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }    

    public List<CBCRequestParam> getParams() {
        return params;
    }

    public void setParams(List<CBCRequestParam> params) {
        this.params = params;
    }

    public CBCUser getAsignee() {
        return asignee;
    }

    public void setAsignee(CBCUser asignee) {
        this.asignee = asignee;
    }  

    public CBCUser getTemplate() {
        return template;
    }

    public void setTemplate(CBCUser template) {
        this.template = template;
    }  
    
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }    
    
    public CBCTiers getTiers() {
        return tiers;
    }

    public void setTiers(CBCTiers tiers) {
        this.tiers = tiers;
    }    
}

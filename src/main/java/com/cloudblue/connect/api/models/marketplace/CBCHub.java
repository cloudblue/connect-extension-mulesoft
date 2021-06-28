package com.cloudblue.connect.api.models.marketplace;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.common.CBCUser;
import com.cloudblue.connect.api.models.common.CBCEvents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCHub extends CBCCommonEntity {

    @JsonProperty
    private CBCUser company;

    @JsonProperty
    private String description;
    
    @JsonProperty
    private CBCEvents events;
 
    public CBCUser getCompany() {
        return company;
    }

    public void setCompany(CBCUser company) {
        this.company = company;
    }    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }    

}

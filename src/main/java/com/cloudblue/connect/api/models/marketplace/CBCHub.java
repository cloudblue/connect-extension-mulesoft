package com.cloudblue.connect.api.models.marketplace;

import com.cloudblue.connect.api.models.common.CBCUser;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCHub implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private CBCUser company;

    @JsonProperty
    private String description;
    
    @JsonProperty
    private CBCEvents events;

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

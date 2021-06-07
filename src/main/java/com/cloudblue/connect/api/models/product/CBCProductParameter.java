package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.CBCRequestParamConstraints;
import com.cloudblue.connect.api.models.common.CBCEntity;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCProductParameter implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private String type;

    @JsonProperty
    private String scope;

    @JsonProperty
    private String phase;

    @JsonProperty
    private CBCRequestParamConstraints constraints;

    @JsonProperty
    private Integer position;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public CBCRequestParamConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(CBCRequestParamConstraints constraints) {
        this.constraints = constraints;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }

}

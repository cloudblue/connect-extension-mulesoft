/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.CBCRequestParamConstraints;
import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.enums.CBCProductParameterType;
import com.cloudblue.connect.api.models.enums.CBCProductParameterScope;
import com.cloudblue.connect.api.models.enums.CBCProductParameterPhase;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCProductParameter extends CBCCommonEntity {

    @JsonProperty
    private String title;

    @JsonProperty
    private String description;

    @JsonProperty
    private CBCProductParameterType type;

    @JsonProperty
    private CBCProductParameterScope scope;

    @JsonProperty
    private CBCProductParameterPhase phase;

    @JsonProperty
    private CBCRequestParamConstraints constraints;

    @JsonProperty
    private Integer position;

    @JsonProperty
    private CBCEvents events;

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

    public CBCProductParameterType getType() {
        return type;
    }

    public void setType(CBCProductParameterType type) {
        this.type = type;
    }

    public CBCProductParameterScope getScope() {
        return scope;
    }

    public void setScope(CBCProductParameterScope scope) {
        this.scope = scope;
    }

    public CBCProductParameterPhase getPhase() {
        return phase;
    }

    public void setPhase(CBCProductParameterPhase phase) {
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.enums.CBCTemplateScope;
import com.cloudblue.connect.api.models.enums.CBCTemplateType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CBCTemplate extends CBCCommonEntity {

    @JsonProperty
    private String title;

    @JsonProperty
    private String body;

    @JsonProperty
    private Integer position;

    @JsonProperty
    private CBCTemplateType type;

    @JsonProperty
    private CBCTemplateScope scope;

    @JsonProperty
    private CBCEvents events;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public CBCTemplateType getType() {
        return type;
    }

    public void setType(CBCTemplateType type) {
        this.type = type;
    }

    public CBCTemplateScope getScope() {
        return scope;
    }

    public void setScope(CBCTemplateScope scope) {
        this.scope = scope;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}

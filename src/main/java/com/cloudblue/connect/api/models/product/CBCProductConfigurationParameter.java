package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCEntity;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCProductConfigurationParameter implements CBCEntity {

    @JsonProperty
    private String value;

    @JsonProperty
    private CBCProductParameter parameter;

    @JsonProperty
    private CBCProductItem item;

    @JsonProperty
    private CBCMarketplace marketplace;

    @JsonProperty
    private CBCEvents events;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CBCProductParameter getParameter() {
        return parameter;
    }

    public void setParameter(CBCProductParameter parameter) {
        this.parameter = parameter;
    }

    public CBCProductItem getItem() {
        return item;
    }

    public void setItem(CBCProductItem item) {
        this.item = item;
    }

    public CBCMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(CBCMarketplace marketplace) {
        this.marketplace = marketplace;
    }

     public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}

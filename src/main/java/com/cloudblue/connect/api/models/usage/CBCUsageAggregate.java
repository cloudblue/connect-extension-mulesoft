/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.usage;

import com.cloudblue.connect.api.models.product.CBCProductItem;
import com.cloudblue.connect.api.models.common.CBCEntity;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageAggregate implements CBCEntity {
    @JsonProperty
    private CBCProductItem item;

    @JsonProperty
    private CBCAsset asset;

    @JsonProperty
    private String currency;

    @JsonProperty
    private Double amount;

    @JsonProperty
    private Double consumed;

    @JsonProperty
    private Double accepted;

    @JsonProperty
    private CBCEvents events;

    public CBCProductItem getItem() {
        return item;
    }

    public void setItem(CBCProductItem item) {
        this.item = item;
    }

    public CBCAsset getAsset() {
        return asset;
    }

    public void setAsset(CBCAsset asset) {
        this.asset = asset;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConsumed() {
        return consumed;
    }

    public void setConsumed(Double consumed) {
        this.consumed = consumed;
    }

    public Double getAccepted() {
        return accepted;
    }

    public void setAccepted(Double accepted) {
        this.accepted = accepted;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}

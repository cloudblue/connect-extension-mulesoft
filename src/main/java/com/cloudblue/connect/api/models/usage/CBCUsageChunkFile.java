/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.usage;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.common.CBCTenant;
import com.cloudblue.connect.api.models.enums.CBCChunkFileStatus;
import com.cloudblue.connect.api.models.marketplace.CBCHubBinding;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageChunkFile implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty
    private CBCChunkFileStatus status;

    @JsonProperty
    private Boolean outdated;

    @JsonProperty("usagefile")
    private CBCUsageReport usageReport;

    @JsonProperty
    private CBCMarketplace marketplace;

    @JsonProperty
    private Map<String, String> error;

    @JsonProperty
    private CBCEvents events;

    @JsonProperty
    private CBCTenant provider;

    @JsonProperty
    private CBCHubBinding binding;

    @JsonProperty
    private CBCUsageStats stats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public CBCChunkFileStatus getStatus() {
        return status;
    }

    public void setStatus(CBCChunkFileStatus status) {
        this.status = status;
    }

    public Boolean getOutdated() {
        return outdated;
    }

    public void setOutdated(Boolean outdated) {
        this.outdated = outdated;
    }

    public CBCUsageReport getUsageReport() {
        return usageReport;
    }

    public void setUsageReport(CBCUsageReport usageReport) {
        this.usageReport = usageReport;
    }

    public CBCMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(CBCMarketplace marketplace) {
        this.marketplace = marketplace;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }

    public CBCTenant getProvider() {
        return provider;
    }

    public void setProvider(CBCTenant provider) {
        this.provider = provider;
    }

    public CBCHubBinding getBinding() {
        return binding;
    }

    public void setBinding(CBCHubBinding binding) {
        this.binding = binding;
    }

    public CBCUsageStats getStats() {
        return stats;
    }

    public void setStats(CBCUsageStats stats) {
        this.stats = stats;
    }
}

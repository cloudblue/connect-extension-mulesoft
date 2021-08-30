/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.enums.CBCWebhookEventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCWebhookEvent {
    @JsonProperty
    private String id;

    @JsonProperty("webhook_id")
    private String webhookId;

    @JsonProperty("webhook_name")
    private String webhookName;

    @JsonProperty("object_class")
    private CBCWebhookEventType eventType;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("api_url")
    private String apiUrl;

    @JsonProperty("triggered_at")
    private Date triggeredAt;

    @JsonProperty("last_success_at")
    private Date lastSuccessAt;

    @JsonProperty("last_failure_at")
    private Date lastFailureAt;

    @JsonProperty("processing_timeout")
    private Integer processingTimeout;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebhookId() {
        return webhookId;
    }

    public void setWebhookId(String webhookId) {
        this.webhookId = webhookId;
    }

    public String getWebhookName() {
        return webhookName;
    }

    public void setWebhookName(String webhookName) {
        this.webhookName = webhookName;
    }

    public CBCWebhookEventType getEventType() {
        return eventType;
    }

    public void setEventType(CBCWebhookEventType eventType) {
        this.eventType = eventType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Date getTriggeredAt() {
        return triggeredAt;
    }

    public void setTriggeredAt(Date triggeredAt) {
        this.triggeredAt = triggeredAt;
    }

    public Date getLastSuccessAt() {
        return lastSuccessAt;
    }

    public void setLastSuccessAt(Date lastSuccessAt) {
        this.lastSuccessAt = lastSuccessAt;
    }

    public Date getLastFailureAt() {
        return lastFailureAt;
    }

    public void setLastFailureAt(Date lastFailureAt) {
        this.lastFailureAt = lastFailureAt;
    }

    public Integer getProcessingTimeout() {
        return processingTimeout;
    }

    public void setProcessingTimeout(Integer processingTimeout) {
        this.processingTimeout = processingTimeout;
    }
}

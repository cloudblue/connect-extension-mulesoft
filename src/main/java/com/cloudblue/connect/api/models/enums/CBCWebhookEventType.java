package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCWebhookEventType {
    @JsonProperty("fulfillment_request")
    FULFILLMENT_REQUEST,

    @JsonProperty("billing_request")
    BILLING_REQUEST,

    @JsonProperty("listing_request")
    LISTING_REQUEST
}

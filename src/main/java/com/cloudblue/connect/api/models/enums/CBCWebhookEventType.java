/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CBCWebhookEventType {
    @JsonProperty("fulfillment_request")
    FULFILLMENT_REQUEST,

    @JsonProperty("billing_request")
    BILLING_REQUEST,

    @JsonProperty("listing_request")
    LISTING_REQUEST,

    @JsonProperty("tier_account_request")
    TIER_ACCOUNT_REQUEST,

    @JsonProperty("tier_config_request")
    TIER_CONFIG_REQUEST,

    @JsonProperty("usage_file")
    USAGE_FILE
}

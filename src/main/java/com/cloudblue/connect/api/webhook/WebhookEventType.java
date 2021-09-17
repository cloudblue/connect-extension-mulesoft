/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.webhook;

public enum WebhookEventType {
    FULFILLMENT_REQUEST,
    BILLING_REQUEST,
    LISTING_REQUEST,
    TIER_ACCOUNT_REQUEST,
    TIER_CONFIG_REQUEST,
    USAGE_FILE
}

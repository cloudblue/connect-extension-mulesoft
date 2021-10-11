/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.api.parameters;

public enum ResourceType {
    REQUEST, ASSET, SUBSCRIPTION_REQUEST, SUBSCRIPTION_ASSET,
    TIER_ACCOUNT, TIER_ACCOUNT_VERSION, TIER_ACCOUNT_REQUEST,
    TIER_CONFIG_REQUEST, TIER_CONFIG, PRODUCT, PRODUCT_TEMPLATE,
    PRODUCT_ACTION, PRODUCT_ITEM, PRODUCT_PARAMETER, PRODUCT_CONFIGURATION,
    USAGE_REPORT, USAGE_RECORD, USAGE_CHUNK, USAGE_RECONCILIATION,
    USAGE_AGGREGATE, ASSET_USAGE_AGGREGATE, CASE, CONVERSATION_MESSAGES,
    PRODUCT_ACTION_LINK, BILLING_REQUEST_ATTRIBUTE
}

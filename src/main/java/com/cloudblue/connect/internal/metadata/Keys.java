/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

public enum Keys {
    ID("id", "ID"),
    EFFECTIVE_DATE("effective_date", "Effective Date"),
    TEMPLATE("template", "Template"),
    REASON("reason", "Reason of action"),
    RATING("rating", "Rating"),
    FEEDBACK("feedback", "Feedback"),
    TEMPLATE_ID("template_id", "Template ID"),
    EMAIL("email", "Email"),
    FORCE("force", "Force Assignment"),
    EXTERNAL_ID("external_id", "External ID"),
    EXTERNAL_BILLING_ID("external_billing_id", "External Billing ID"),
    EXTERNAL_BILLING_NOTE("external_billing_note", "External Billing Note"),
    ACCEPTANCE_NOTE("acceptance_note", "Acceptance Note"),
    REJECTION_NOTE("rejection_note", "Rejection Note"),
    ASSET("asset", "Asset"),
    PARAMS("params", "Params"),
    VALUE("value", "Value"),
    STRUCTURED_VALUE("structured_value", "Structure Value"),
    VALUE_ERROR("value_error", "Error Details of Value"),
    REQUEST_ID("request_id", "Request ID"),
    ASSET_ID("asset_id", "Asset ID"),
    TA_ID("tier_account_id", "Tier Account ID"),
    TA_VERSION_ID("tier_account_version", "Tier Account Version"),
    TAR_ID("tier_account_request_id", "Tier Account Request ID"),
    TC_ID("tier_config_id", "Tier Config ID"),
    TCR_ID("tier_config_request_id", "Tier Config Request ID"),
    PRODUCT_ID("product_id", "Product ID"),
    USAGE_REPORT_ID("usage_report_id", "Usage Report ID"),
    USAGE_RECORD_ID("usage_record_id", "Usage Record ID"),
    USAGE_CHUNK_ID("usage_chunk_id", "Usage Chunk ID"),
    USAGE_RECON_ID("usage_recon_id", "Usage Recon ID"),
    CASE_ID("case_id", "Case ID"),
    MESSAGE_ID("message_id", "Message ID"),
    CONVERSATION_ID("conversation_id", "Conversation ID"),
    ACTION_ID("action_id", "Action ID"),
    ITEM_ID("item_id", "Item ID"),
    PARAMETER_ID("parameter_id", "Parameter ID");

    private final String field;
    private final String label;

    Keys(String field, String label) {
        this.field = field;
        this.label = label;
    }

    public String getField() {
        return field;
    }

    public String getLabel() {
        return label;
    }
}

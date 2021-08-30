/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.enums;

public enum TCRValidationType {
    DRAFT_TCR_T1_VALIDATION("draft_tcr_t1_val"),
    DRAFT_TCR_T2_VALIDATION("draft_tcr_t2_val"),
    INQUIRING_TCR_T1_VALIDATION("tcr_setup_t1_inq_val"),
    INQUIRING_TCR_T2_VALIDATION("tcr_setup_t2_inq_val");

    private String type;

    TCRValidationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

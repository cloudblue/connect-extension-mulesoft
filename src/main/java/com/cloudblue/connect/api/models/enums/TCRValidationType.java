package com.cloudblue.connect.api.models.enums;

public enum TCRValidationType {
    Draft_TCR_T1_Validator("draft_tcr_t1_val"),
    Draft_TCR_T2_Validator("draft_tcr_t2_val"),
    Inquiring_TCR_T1_Validator("tcr_setup_t1_inq_val"),
    Inquiring_TCR_T2_Validator("tcr_setup_t2_inq_val");

    private String type;

    TCRValidationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

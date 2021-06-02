package com.cloudblue.connect.api.parameters.usage.chunk;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CloseChunkFile extends ChunkFileAction {

    @Parameter
    private String externalBillingId;

    @Parameter
    @Optional
    private String externalBillingNote;

    public String getExternalBillingId() {
        return externalBillingId;
    }

    public void setExternalBillingId(String externalBillingId) {
        this.externalBillingId = externalBillingId;
    }

    public String getExternalBillingNote() {
        return externalBillingNote;
    }

    public void setExternalBillingNote(String externalBillingNote) {
        this.externalBillingNote = externalBillingNote;
    }

    @Override
    public Object buildEntity() {
        addValue("external_billing_id", externalBillingId);
        addValue("external_billing_note", externalBillingNote);

        return payload;
    }

    @Override
    public String action() {
        return "close";
    }
}

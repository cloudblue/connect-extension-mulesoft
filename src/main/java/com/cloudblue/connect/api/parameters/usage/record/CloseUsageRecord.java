package com.cloudblue.connect.api.parameters.usage.record;

import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.api.parameters.Embeddable;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class CloseUsageRecord implements Embeddable {
    @Parameter
    private String id;

    @Parameter
    @Optional
    private String externalBillingId;

    @Parameter
    @Optional
    private String externalBillingNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        CBCUsageRecord record = new CBCUsageRecord();
        record.setId(id);
        record.setExternalBillingId(externalBillingId);
        record.setExternalBillingNote(externalBillingNote);

        return record;
    }
}

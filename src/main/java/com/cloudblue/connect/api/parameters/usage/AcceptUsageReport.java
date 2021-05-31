package com.cloudblue.connect.api.parameters.usage;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class AcceptUsageReport extends UsageReportAction {

    @Parameter
    @Expression
    @Placement(order = 2)
    private String acceptanceNote;

    public String getAcceptanceNote() {
        return acceptanceNote;
    }

    public void setAcceptanceNote(String acceptanceNote) {
        this.acceptanceNote = acceptanceNote;
    }

    @Override
    public Object buildEntity() {
        addValue("acceptance_note", acceptanceNote);

        return payload;
    }

    @Override
    public String action() {
        return "accept";
    }
}

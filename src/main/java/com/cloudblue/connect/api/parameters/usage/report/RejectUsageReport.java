package com.cloudblue.connect.api.parameters.usage.report;

import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class RejectUsageReport extends UsageReportAction {

    @Parameter
    @Expression
    @Placement(order = 2)
    private String rejectionNote;

    public String getRejectionNote() {
        return rejectionNote;
    }

    public void setRejectionNote(String rejectionNote) {
        this.rejectionNote = rejectionNote;
    }

    @Override
    public Object buildEntity() {
        addValue("rejection_note", rejectionNote);

        return payload;
    }

    @Override
    public String action() {
        return "accept";
    }
}

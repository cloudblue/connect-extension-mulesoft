package com.cloudblue.connect.api.parameters.usage.report;

public class DeleteUsageReport extends UsageReportAction {
    @Override
    public Object buildEntity() {
        return null;
    }

    @Override
    public String action() {
        return "delete";
    }
}

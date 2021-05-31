package com.cloudblue.connect.api.parameters.usage;

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

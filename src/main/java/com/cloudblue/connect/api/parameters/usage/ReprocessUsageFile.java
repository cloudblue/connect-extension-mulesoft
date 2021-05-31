package com.cloudblue.connect.api.parameters.usage;

public class ReprocessUsageFile extends UsageReportAction {
    @Override
    public Object buildEntity() {
        return null;
    }

    @Override
    public String action() {
        return "reprocess";
    }
}

package com.cloudblue.connect.api.parameters.usage;

public class CloseUsageReport extends UsageReportAction {
    @Override
    public Object buildEntity() {
        return null;
    }

    @Override
    public String action() {
        return "close";
    }
}

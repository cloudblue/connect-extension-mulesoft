package com.cloudblue.connect.api.parameters.usage.report;


import org.mule.runtime.extension.api.annotation.param.Parameter;

public class UpdateUsageReportParameter extends BaseUsageReportParameter {
    @Parameter
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

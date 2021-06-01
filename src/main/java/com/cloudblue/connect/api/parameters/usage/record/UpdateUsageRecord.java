package com.cloudblue.connect.api.parameters.usage.record;

import com.cloudblue.connect.api.parameters.common.AbstractResourceMultiAction;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class UpdateUsageRecord extends AbstractResourceMultiAction {

    @Parameter
    @Optional(defaultValue = "closed")
    private String status;

    @Override
    public Object buildEntity() {
        addValue("status", status);

        return payload;
    }

    @Override
    public String action() {
        return null;
    }
}

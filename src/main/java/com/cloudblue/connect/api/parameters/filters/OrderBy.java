package com.cloudblue.connect.api.parameters.filters;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.List;

public class OrderBy {
    @Parameter
    private List<String> properties;

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}

package com.cloudblue.connect.api.parameters.filters;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.List;

public abstract class MultiValueFilter implements Filter {
    @Parameter
    private String property;

    @Parameter
    private List<String> values;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.filters;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.io.Serializable;
import java.util.List;

public abstract class MultiValueFilter implements Filter, Serializable {
    private static final long serialVersionUID = -6100341255807437427L;
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

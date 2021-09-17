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

public class OrderBy implements Serializable {
    private static final long serialVersionUID = 3015165648011946081L;

    @Parameter
    private List<String> properties;

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class UpdateTierAccountParameter extends NewTierAccountParameter {

    @Parameter
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {
        return super.buildEntity();
    }
}

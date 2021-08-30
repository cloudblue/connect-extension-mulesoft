/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageRule implements CBCEntity {

    @JsonProperty
    private Boolean enabled;

    @JsonProperty
    private List<CBCCommonEntity> providers;
    
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }    

    public List<CBCCommonEntity> getProviders() {
        return providers;
    }

    public void setProviders(List<CBCCommonEntity> providers) {
        this.providers = providers;
    }    
}

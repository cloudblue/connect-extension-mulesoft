/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCBillingAttributes {

    @JsonProperty
    private Map<String, String> vendor;

    @JsonProperty
    private Map<String, String> provider;

    public Map<String, String> getVendor() {
        return vendor;
    }

    public void setVendor(Map<String, String> vendor) {
        this.vendor = vendor;
    }

    public Map<String, String> getProvider() {
        return provider;
    }

    public void setProvider(Map<String, String> provider) {
        this.provider = provider;
    }
}

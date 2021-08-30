/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCEvent implements CBCEntity {

    @JsonProperty
    private String at;

    @JsonProperty
    private CBCUser by;

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }    

    public CBCUser getBy() {
        return by;
    }

    public void setBy(CBCUser by) {
        this.by = by;
    }  
}

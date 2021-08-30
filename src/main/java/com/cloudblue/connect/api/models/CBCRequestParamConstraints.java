/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCRequestParamConstraints {

    @Parameter
    @JsonProperty
    private Boolean required;

    @Parameter
    @JsonProperty
    public Boolean hidden;

    @Parameter
    @JsonProperty
    public Boolean unique;

    @Parameter
    @JsonProperty
    public Boolean reconciliation;

    @Parameter
    @JsonProperty
    public Boolean shared;

    @JsonProperty
    public String placeholder;

    @JsonProperty
    public String hint;

    @JsonProperty
    public Map<String, String> meta;

    @JsonProperty("default")
    public Map<String, String> isDefault;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    public Boolean getReconciliation() {
        return reconciliation;
    }

    public void setReconciliation(Boolean reconciliation) {
        this.reconciliation = reconciliation;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    public Map<String, String> getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Map<String, String> isDefault) {
        this.isDefault = isDefault;
    }
}

package com.cloudblue.connect.api.models.subscription;

import com.cloudblue.connect.api.models.CBCRequestParamConstraints;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCRequestParam implements CBCEntity {
    @Parameter
    @JsonProperty
    private String id;

    @Parameter
    @Optional    
    @JsonProperty
    private String title;

    @Parameter
    @Optional    
    @JsonProperty
    private String description;
    
    @Parameter
    @JsonProperty
    private String value;

    @Parameter
    @Optional
    @JsonProperty
    private String structuredValue;

    @Parameter
    @Optional    
    @JsonProperty
    private String valueError;

    @Parameter
    @Optional    
    @JsonProperty
    private String type;

    @Parameter
    @Optional    
    @JsonProperty
    private String scope;

    @Parameter
    @Optional    
    @JsonProperty
    private String phase;

    @Parameter
    @Optional    
    @JsonProperty
    private CBCRequestParamConstraints constraints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueError() {
        return valueError;
    }

    public void setValueError(String valueError) {
        this.valueError = valueError;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
    public CBCRequestParamConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(CBCRequestParamConstraints constraints) {
        this.constraints = constraints;
    }

    public String getStructuredValue() {
        return structuredValue;
    }

    public void setStructuredValue(String structuredValue) {
        this.structuredValue = structuredValue;
    }
}

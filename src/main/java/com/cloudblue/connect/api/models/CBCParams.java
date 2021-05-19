package com.cloudblue.connect.api.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCParams {
    @Parameter
    @JsonProperty
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }    

    public String getStructuredValue() {
        return structuredValue;
    }

    public void setStructuredValue(String structuredValue) {
        this.structuredValue = structuredValue;
    }

    public String getValueError() {
        return valueError;
    }

    public void setValueError(String valueError) {
        this.valueError = valueError;
    }
}

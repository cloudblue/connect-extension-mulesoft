package com.cloudblue.connect.api.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCBillingRequest {
    @JsonProperty
    private String id;

    @JsonProperty
    private String type;

    @JsonProperty
    private CBCBillingPeriod period;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CBCBillingPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CBCBillingPeriod period) {
        this.period = period;
    }
}

package com.cloudblue.connect.api.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCBillingStat {
    @JsonProperty("last_request")
    private CBCBillingRequest lastRequest;

    @JsonProperty
    private Integer count;

    public CBCBillingRequest getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(CBCBillingRequest lastRequest) {
        this.lastRequest = lastRequest;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
package com.cloudblue.connect.api.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCBillingStats {
    @JsonProperty
    private CBCBillingStat vendor;

    @JsonProperty
    private CBCBillingStat provider;

    public CBCBillingStat getVendor() {
        return vendor;
    }

    public void setVendor(CBCBillingStat vendor) {
        this.vendor = vendor;
    }

    public CBCBillingStat getProvider() {
        return provider;
    }

    public void setProvider(CBCBillingStat provider) {
        this.provider = provider;
    }
}

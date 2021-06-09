package com.cloudblue.connect.api.models.tier;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.common.CBCUser;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCAccountRequest implements CBCEntity {
    @Parameter
    @Optional
    @JsonProperty
    private String id;

    @Parameter
    @Optional
    @JsonProperty
    private String type;
    
    @Parameter
    @JsonProperty
    private CBCAccount account;

    @Parameter
    @JsonProperty
    private CBCUser provider;

    @Parameter
    @JsonProperty
    private CBCUser vendor;

    @Parameter
    @JsonProperty
    private CBCProduct product;

    @Parameter
    @JsonProperty
    private String status;

    @Parameter
    @Optional
    @JsonProperty
    private CBCEvents events;



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

    public CBCAccount getAccount() {
        return account;
    }

    public void setAccount(CBCAccount account) {
        this.account = account;
    }

    public CBCUser getProvider() {
        return provider;
    }

    public void setProvider(CBCUser provider) {
        this.provider = provider;
    }

    public CBCUser getVendor() {
        return vendor;
    }

    public void setVendor(CBCUser vendor) {
        this.vendor = vendor;
    }

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }    

}

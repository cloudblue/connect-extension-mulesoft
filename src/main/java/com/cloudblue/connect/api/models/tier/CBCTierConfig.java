package com.cloudblue.connect.api.models.tier;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.common.CBCEvent;
import com.cloudblue.connect.api.models.common.CBCUser;
import com.cloudblue.connect.api.models.marketplace.CBCConnection;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.subscription.CBCRequestParam;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCTierConfig implements CBCEntity {
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private CBCAccount account;

    @JsonProperty
    private CBCProduct product;

    @JsonProperty
    private String tierLevel;

    @JsonProperty
    private CBCConnection connection;

    @JsonProperty
    private CBCEvent events;

    @JsonProperty
    private List<CBCRequestParam> params;

    @JsonProperty
    private CBCUser template;

    @JsonProperty
    private String status;

    @JsonProperty
    private CBCMarketplace marketplace;

    @JsonProperty
    private CBCTiers tiers;  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CBCAccount getAccount() {
        return account;
    }

    public void setAccount(CBCAccount account) {
        this.account = account;
    }

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
    }

    public String getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(String tierLevel) {
        this.tierLevel = tierLevel;
    }
    
    public CBCConnection getConnection() {
        return connection;
    }

    public void setConnection(CBCConnection connection) {
        this.connection = connection;
    }

    public CBCEvent getEvents() {
        return events;
    }

    public void setEvents(CBCEvent events) {
        this.events = events;
    }    

    public List<CBCRequestParam> getParams() {
        return params;
    }

    public void setParams(List<CBCRequestParam> params) {
        this.params = params;
    }

    public CBCUser getTemplate() {
        return template;
    }

    public void setTemplate(CBCUser template) {
        this.template = template;
    }      

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public CBCMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(CBCMarketplace marketplace) {
        this.marketplace = marketplace;
    }    
    
    public CBCTiers getTiers() {
        return tiers;
    }

    public void setTiers(CBCTiers tiers) {
        this.tiers = tiers;
    }    
}

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.enums.CBCAssetStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCAsset {
    @JsonProperty
    private String id;

    @JsonProperty
    private CBCAssetStatus status;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("external_uid")
    private String externalUid;

    @JsonProperty
    private CBCConnection connection;

    @JsonProperty
    private CBCProduct product;

    @JsonProperty
    private List<CBCRequestItem> items;

    @JsonProperty
    private List<CBCRequestParam> params;

    @JsonProperty
    private Map<String, CBCAccount> tiers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CBCAssetStatus getStatus() {
        return status;
    }

    public void setStatus(CBCAssetStatus status) {
        this.status = status;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalUid() {
        return externalUid;
    }

    public void setExternalUid(String externalUid) {
        this.externalUid = externalUid;
    }

    public CBCConnection getConnection() {
        return connection;
    }

    public void setConnection(CBCConnection connection) {
        this.connection = connection;
    }

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
    }

    public List<CBCRequestItem> getItems() {
        return items;
    }

    public void setItems(List<CBCRequestItem> items) {
        this.items = items;
    }

    public List<CBCRequestParam> getParams() {
        return params;
    }

    public void setParams(List<CBCRequestParam> params) {
        this.params = params;
    }

    public Map<String, CBCAccount> getTiers() {
        return tiers;
    }

    public void setTiers(Map<String, CBCAccount> tiers) {
        this.tiers = tiers;
    }
    
}

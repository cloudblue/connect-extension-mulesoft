package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.enums.CBCAssetStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCAsset {
    private String id;
    private CBCAssetStatus status;
    private String externalId;
    private String externalUid;
    private CBCConnection connection;
    private CBCProduct product;
    private List<CBCRequestItem> items;
    private List<CBCRequestParam> params;
    private Map<String, CBCOrganization> tiers;

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

    public Map<String, CBCOrganization> getTiers() {
        return tiers;
    }

    public void setTiers(Map<String, CBCOrganization> tiers) {
        this.tiers = tiers;
    }
    
}

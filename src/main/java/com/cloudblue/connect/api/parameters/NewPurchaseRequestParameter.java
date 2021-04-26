package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.models.enums.CBCRequestType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewPurchaseRequestParameter implements Embeddable {
    
    @Parameter
    private String productId;
    
    @Parameter
    private String connectionId;
    
    @Parameter
    private String marketplaceId;
    
    @Parameter
    private String externalId;
    
    @Parameter
    @Optional
    private String externalUid = UUID.randomUUID().toString();
    
    @Parameter
    private CBCAccount customer;
    
    @Parameter
    @Optional
    private CBCAccount tier1;
    
    @Parameter
    @Optional
    private CBCAccount tier2;
    
    @Parameter
    private List<CBCRequestItem> items;
    
    @Parameter
    @Optional
    private List<CBCRequestParam> params;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalUid() {
        if (externalUid == null || externalUid.isEmpty())
            externalUid = UUID.randomUUID().toString();
        return externalUid;
    }

    public void setExternalUid(String externalUid) {
        this.externalUid = externalUid;
    }

    public CBCAccount getCustomer() {
        return customer;
    }

    public void setCustomer(CBCAccount customer) {
        this.customer = customer;
    }

    public CBCAccount getTier1() {
        return tier1;
    }

    public void setTier1(CBCAccount tier1) {
        this.tier1 = tier1;
    }

    public CBCAccount getTier2() {
        return tier2;
    }

    public void setTier2(CBCAccount tier2) {
        this.tier2 = tier2;
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

    @Override
    @JsonIgnore
    public Object buildEntity() {

        CBCRequest request = new CBCRequest();
        request.setType(CBCRequestType.PURCHASE);

        request.setAsset(new CBCAsset());
        request.getAsset().setParams(this.params);
        request.getAsset().setItems(this.items);
        request.getAsset().setProduct(new CBCProduct());
        request.getAsset().getProduct().setId(this.productId);
        request.getAsset().setExternalId(this.getExternalId());
        request.getAsset().setExternalUid(this.getExternalUid());

        if (
                this.getCustomer().getExternalUid() == null
                        || this.getCustomer().getExternalUid().isEmpty()
        )
            this.getCustomer().setExternalUid(UUID.randomUUID().toString());

        request.getAsset().setTiers(new HashMap<>());
        request.getAsset().getTiers().put("customer", this.getCustomer());
        request.getAsset().getTiers().put("tier1", this.getTier1());
        request.getAsset().getTiers().put("tier2", this.getTier2());

        request.getAsset().setConnection(new CBCConnection());
        request.getAsset().getConnection().setId(this.getConnectionId());

        request.setMarketplace(new CBCMarketplace());
        request.getMarketplace().setId(this.marketplaceId);

        return request;
    }
}

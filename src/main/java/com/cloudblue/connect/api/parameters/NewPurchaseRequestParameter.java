package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.enums.CBCRequestType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.cloudblue.connect.api.models.marketplace.CBCConnection;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.models.subscription.CBCRequestItem;
import com.cloudblue.connect.api.models.subscription.CBCRequestParam;
import com.cloudblue.connect.api.models.tier.CBCAccount;
import com.cloudblue.connect.api.parameters.accounts.AccountParameter;
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
    private AccountParameter customer;
    
    @Parameter
    @Optional
    private AccountParameter tier1;
    
    @Parameter
    @Optional
    private AccountParameter tier2;
    
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

    public AccountParameter getCustomer() {
        return customer;
    }

    public void setCustomer(AccountParameter customer) {
        this.customer = customer;
    }

    public AccountParameter getTier1() {
        return tier1;
    }

    public void setTier1(AccountParameter tier1) {
        this.tier1 = tier1;
    }

    public AccountParameter getTier2() {
        return tier2;
    }

    public void setTier2(AccountParameter tier2) {
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

        CBCAccount account = new CBCAccount();
        account.setCompanyName(this.getCustomer().getCompanyName());
        account.setExternalId(this.getCustomer().getExternalId());
        account.setExternalUid(this.getCustomer().getExternalUid());
        account.setContactInfo(this.getCustomer().getContactInfo());
        request.getAsset().getTiers().put("customer", account);

        if (this.getTier1() != null) {

            account.setCompanyName(this.getTier1().getCompanyName());
            account.setExternalId(this.getTier1().getExternalId());
            account.setExternalUid(this.getTier1().getExternalUid());
            account.setContactInfo(this.getTier1().getContactInfo());
            request.getAsset().getTiers().put("tier1", account);
        }

        if (this.getTier2() != null) {
            account.setCompanyName(this.getTier2().getCompanyName());
            account.setExternalId(this.getTier2().getExternalId());
            account.setExternalUid(this.getTier2().getExternalUid());
            account.setContactInfo(this.getTier2().getContactInfo());
            request.getAsset().getTiers().put("tier2", account);
        }
        request.getAsset().setConnection(new CBCConnection());
        request.getAsset().getConnection().setId(this.getConnectionId());

        request.setMarketplace(new CBCMarketplace());
        request.getMarketplace().setId(this.marketplaceId);

        return request;
    }
}

package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.parameters.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import java.util.List;
import java.util.ArrayList;

public class NewTierConfigParameter implements Embeddable {
    
    @Parameter
    private String productId;

    @Parameter
    private String tierLevel;

    @Parameter
    private String connectionId;

    @Parameter
    private String marketplaceId;

    @Parameter
    private String paramId;

    @Parameter
    private String paramValue;
    
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(String tierLevel) {
        this.tierLevel = tierLevel;
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

    public String getParamId() {
        return paramId;
    }

    public void setParamId(String paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setPaaramValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {
        CBCConfiguration configuration = new CBCConfiguration();
        configuration.setProduct(new CBCProduct());
        configuration.getProduct().setId(this.productId);
        configuration.setTierLevel(this.tierLevel);
        configuration.setConnection(new CBCConnection());
        configuration.getConnection().setId(this.connectionId);
        configuration.setMarketplace(new CBCMarketplace());
        configuration.getMarketplace().setId(this.marketplaceId);

        List<CBCParams> recipients = new ArrayList<CBCParams>();
        CBCParams element = new CBCParams();
        recipients.add(element);
        element.setId(this.paramId);
        element.setValue(this.paramValue);
        configuration.setParams(recipients);
        return configuration;    
    }
}

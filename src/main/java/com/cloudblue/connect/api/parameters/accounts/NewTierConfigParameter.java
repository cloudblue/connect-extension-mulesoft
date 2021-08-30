/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.marketplace.CBCConnection;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.subscription.CBCRequestParam;
import com.cloudblue.connect.api.models.tier.CBCTierConfig;
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

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {
        CBCTierConfig configuration = new CBCTierConfig();
        configuration.setProduct(new CBCProduct());
        configuration.getProduct().setId(this.productId);
        configuration.setTierLevel(this.tierLevel);
        configuration.setConnection(new CBCConnection());
        configuration.getConnection().setId(this.connectionId);
        configuration.setMarketplace(new CBCMarketplace());
        configuration.getMarketplace().setId(this.marketplaceId);


        CBCRequestParam param = new CBCRequestParam();
        param.setId(this.paramId);
        param.setValue(this.paramValue);

        List<CBCRequestParam> params = new ArrayList<>();
        params.add(param);

        configuration.setParams(params);

        return configuration;    
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCVisibility implements CBCEntity {

    @JsonProperty
    private Boolean owner;

    @JsonProperty
    private Boolean listing;

    @JsonProperty
    private Boolean syndication;
 
    @JsonProperty
    private Boolean catalog;
    
    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }    

    public Boolean getListing() {
        return listing;
    }

    public void setListing(Boolean listing) {
        this.listing = listing;
    }    

    public Boolean getSyndication() {
        return syndication;
    }

    public void setSyndication(Boolean syndication) {
        this.syndication = syndication;
    }   
    
    public Boolean getCatalog() {
        return catalog;
    }

    public void setCatalog(Boolean catalog) {
        this.catalog = catalog;
    }       
}

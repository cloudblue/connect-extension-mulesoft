package com.cloudblue.connect.api.models;

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

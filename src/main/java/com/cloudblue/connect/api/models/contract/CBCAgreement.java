package com.cloudblue.connect.api.models.contract;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCAgreement implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String title;

    @JsonProperty
    private CBCMarketplace marketplace;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }    

    public CBCMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(CBCMarketplace marketplace) {
        this.marketplace = marketplace;
    }    


}

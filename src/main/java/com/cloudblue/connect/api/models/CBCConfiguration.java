package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCConfiguration implements CBCEntity {

    @Parameter    
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private CBCAccount account;

    @Parameter
    @JsonProperty
    private CBCProduct product;

    @Parameter
    @JsonProperty
    private String tierLevel;

    @Parameter
    @JsonProperty
    private CBCConnection connection;

    @JsonProperty
    private CBCEvent events;

    @Parameter
    @JsonProperty
    private CBCTemplate template;    

    @Parameter
    @JsonProperty
    private String status;    

    @Parameter
    @JsonProperty
    private CBCMarketplace marketplace;    

    @Parameter
    @JsonProperty
    @Optional
    private List<CBCParams> params;    
    
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
    
    public List<CBCParams> getParams() {
        return params;
    }

    public void setParams(List<CBCParams> params) {
        this.params = params;
    }        

    public CBCTemplate getTemplate() {
        return template;
    }

    public void setTemplate(CBCTemplate template) {
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
}

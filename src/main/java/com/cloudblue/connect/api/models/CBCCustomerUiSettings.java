package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCustomerUiSettings implements CBCEntity {

    @JsonProperty
    private String description;

    @JsonProperty
    private String gettingStarted;

    @JsonProperty
    private CBCLinks downloadLinks;
    
    @JsonProperty
    private CBCLinks documents;

    @JsonProperty
    private CBCCommonEntity languages;

    @JsonProperty
    private String provisioningMessage;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    

    public String getGettingStarted() {
        return gettingStarted;
    }

    public void setGettingStarted(String gettingStarted) {
        this.gettingStarted = gettingStarted;
    }    

    public CBCLinks getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(CBCLinks downloadLinks) {
        this.downloadLinks = downloadLinks;
    }    

    public CBCLinks getDocuments() {
        return documents;
    }

    public void setDocuments(CBCLinks documents) {
        this.documents = documents;
    }    

    public CBCCommonEntity getLanguages() {
        return languages;
    }

    public void setLanguages(CBCCommonEntity languages) {
        this.languages = languages;
    }    

    public String getProvisioningMessage() {
        return provisioningMessage;
    }

    public void setProvisioningMessage(String provisioningMessage) {
        this.provisioningMessage = provisioningMessage;
    }       
}

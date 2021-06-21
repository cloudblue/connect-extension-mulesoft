package com.cloudblue.connect.api.parameters.accounts;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.marketplace.CBCHub;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.tier.CBCContactInfo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountParameter implements CBCEntity {
    
    @Parameter
    @JsonProperty("name")
    private String companyName;

    @Parameter
    @JsonProperty("external_id")
    private String externalId;
    
    @Parameter
    @Optional
    @JsonProperty("external_uid")
    private String externalUid;

    @Parameter
    @JsonProperty("contact_info")
    private CBCContactInfo contactInfo;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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


    public CBCContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(CBCContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}

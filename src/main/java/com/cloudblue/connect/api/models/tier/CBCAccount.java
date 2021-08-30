/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.tier;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.marketplace.CBCHub;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCAccount implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String version;
    
    @Parameter
    @JsonProperty("name")
    private String companyName;

    @JsonProperty("type")
    private String type;
   
    @Parameter
    @JsonProperty("external_id")
    private String externalId;
    
    @Parameter
    @Optional
    @JsonProperty("external_uid")
    private String externalUid;

    @JsonProperty("parent")
    private CBCAccount parent;
    
    @JsonProperty("owner")
    private CBCAccount owner;

    @JsonProperty("hub")
    private CBCHub hub;

    @JsonProperty("tax_id")
    private String taxId;

    @JsonProperty("events")
    private CBCEvents events;

    @Parameter
    @JsonProperty("contact_info")
    private CBCContactInfo contactInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public CBCAccount getParent() {
        return parent;
    }

    public void setParent(CBCAccount parent) {
        this.parent = parent;
    }

    public CBCAccount getOwner() {
        return owner;
    }

    public void setOwner(CBCAccount owner) {
        this.owner = owner;
    }

    public CBCHub getHub() {
        return hub;
    }

    public void setHub(CBCHub hub) {
        this.hub = hub;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }

    public CBCContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(CBCContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}

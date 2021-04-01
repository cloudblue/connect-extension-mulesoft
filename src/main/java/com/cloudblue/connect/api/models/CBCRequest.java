package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.enums.CBCRequestStatus;
import com.cloudblue.connect.api.models.enums.CBCRequestType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCRequest {
    private String id;
    private CBCRequestType type;
    private String note;
    private String reason;
    private CBCRequestStatus status;
    private CBCAsset asset;
    private Date created;
    private Date updated;
    private String activationKey;
    private boolean answered;
    private Map<String, String> template;
    private Map<String, String> externalAttributes;
    private CBCContract contract;
    private CBCMarketplace marketplace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CBCRequestType getType() {
        return type;
    }

    public void setType(CBCRequestType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CBCRequestStatus getStatus() {
        return status;
    }

    public void setStatus(CBCRequestStatus status) {
        this.status = status;
    }

    public CBCAsset getAsset() {
        return asset;
    }

    public void setAsset(CBCAsset asset) {
        this.asset = asset;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public Map<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(Map<String, String> template) {
        this.template = template;
    }

    public Map<String, String> getExternalAttributes() {
        return externalAttributes;
    }

    public void setExternalAttributes(Map<String, String> externalAttributes) {
        this.externalAttributes = externalAttributes;
    }

    public CBCContract getContract() {
        return contract;
    }

    public void setContract(CBCContract contract) {
        this.contract = contract;
    }

    public CBCMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(CBCMarketplace marketplace) {
        this.marketplace = marketplace;
    }
    
    
    
}

package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.CBCConfigurations;
import com.cloudblue.connect.api.models.CBCCustomerUiSettings;
import com.cloudblue.connect.api.models.CBCUsageRule;
import com.cloudblue.connect.api.models.common.CBCCommonEntity;
import com.cloudblue.connect.api.models.common.CBCEntity;

import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.contract.CBCAgreement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCProduct implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String status;

    @JsonProperty
    private String icon;

    @JsonProperty
    private CBCExtensions replacement;

    @JsonProperty
    private String endOfSaleNotes;

    @JsonProperty
    private String shortDescription;

    @JsonProperty
    private String detailedDescription;

    @JsonProperty
    private CBCConfigurations configurations;

    @JsonProperty
    private CBCCustomerUiSettings customerUiSettings;

    @JsonProperty
    private String versions;

    @JsonProperty
    private CBCCommonEntity category;

    @JsonProperty
    private String publishedAt;

    @JsonProperty
    private CBCCommonEntity owner;

    @JsonProperty
    private String latest;

    @JsonProperty
    private CBCMedia media;

    @JsonProperty
    private CBCUsageRule usageRule;

    @JsonProperty
    private List<CBCAgreement> sourcing;

    @JsonProperty
    private CBCStats stats;

    @JsonProperty
    private CBCCapabilities capabilities;

    @JsonProperty
    private CBCVisibility visibility;

    @JsonProperty
    private String changesDescription;

    @JsonProperty
    private String publicField;

    @JsonProperty
    private CBCEvents events;

    @JsonProperty
    private CBCExtensions extensions;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
  
    public CBCExtensions getReplacement() {
        return replacement;
    }

    public void setReplacement(CBCExtensions replacement) {
        this.replacement = replacement;
    }
    
    public String getEndofSaleNotes() {
        return endOfSaleNotes;
    }

    public void setEndOfSaleNote(String endOfSaleNotes) {
        this.endOfSaleNotes = endOfSaleNotes;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }
    
    public CBCConfigurations getConfigurations() {
        return configurations;
    }

    public void setConfigurations(CBCConfigurations configurations) {
        this.configurations = configurations;
    }

    public CBCCustomerUiSettings getCustomerUiSettings() {
        return customerUiSettings;
    }

    public void setCustomerUiSettings(CBCCustomerUiSettings customerUiSettings) {
        this.customerUiSettings = customerUiSettings;
    }

    public String getVersions() {
        return versions;
    }

    public void setVersions(String versions) {
        this.versions = versions;
    }

    public CBCCommonEntity getCategory() {
        return category;
    }

    public void setCategory(CBCCommonEntity category) {
        this.category = category;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public CBCCommonEntity getOwner() {
        return owner;
    }

    public void setOwner(CBCCommonEntity owner) {
        this.owner = owner;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public CBCMedia getMedia() {
        return media;
    }

    public void setMedia(CBCMedia media) {
        this.media = media;
    }
    
    public CBCUsageRule getUsageRule() {
        return usageRule;
    }

    public void setUsageRule(CBCUsageRule usageRule) {
        this.usageRule = usageRule;
    }

    public List<CBCAgreement> getSourcing() {
        return sourcing;
    }

    public void setSourcing(List<CBCAgreement> sourcing) {
        this.sourcing = sourcing;
    }

    public CBCStats getStats() {
        return stats;
    }

    public void setStats(CBCStats stats) {
        this.stats = stats;
    }

    public CBCCapabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(CBCCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    public CBCVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(CBCVisibility visibility) {
        this.visibility = visibility;
    }

    public String getChangesDescription() {
        return changesDescription;
    }

    public void setChangesDescription(String changesDescription) {
        this.changesDescription = changesDescription;
    }

    public String getPublicField() {
        return publicField;
    }

    public void setPublicField(String publicField) {
        this.publicField = publicField;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }

    public CBCExtensions getExtensions() {
        return extensions;
    }

    public void setExtensions(CBCExtensions extensions) {
        this.extensions = extensions;
    }
}

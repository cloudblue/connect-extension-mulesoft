package com.cloudblue.connect.api.models.usage;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.common.CBCTenant;
import com.cloudblue.connect.api.models.contract.CBCContract;
import com.cloudblue.connect.api.models.enums.CBCUsageReportSchema;
import com.cloudblue.connect.api.models.enums.CBCUsageReportStatus;

import com.cloudblue.connect.api.models.marketplace.CBCMarketplace;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageReport implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String note;

    @JsonProperty
    private CBCUsageReportStatus status;

    @JsonProperty
    private CBCUsagePeriod period;

    @JsonProperty
    private String currency;

    @JsonProperty
    private CBCUsageReportSchema schema;

    @JsonProperty
    private CBCProduct product;

    @JsonProperty
    private CBCContract contract;

    @JsonProperty
    private CBCMarketplace marketplace;

    @JsonProperty
    private CBCTenant vendor;

    @JsonProperty
    private CBCTenant provider;

    @JsonProperty("usage_file_uri")
    private String normalizedFileUri;

    @JsonProperty("processed_file_uri")
    private String processedFileUri;

    @JsonProperty("acceptance_note")
    private String acceptanceNote;

    @JsonProperty("rejection_note")
    private String rejectionNote;

    @JsonProperty("error_detail")
    private String errorDetail;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty
    private CBCUsageStats stats;

    @JsonProperty
    private CBCEvents events;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CBCUsageReportStatus getStatus() {
        return status;
    }

    public void setStatus(CBCUsageReportStatus status) {
        this.status = status;
    }

    public CBCUsagePeriod getPeriod() {
        return period;
    }

    public void setPeriod(CBCUsagePeriod period) {
        this.period = period;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public CBCUsageReportSchema getSchema() {
        return schema;
    }

    public void setSchema(CBCUsageReportSchema schema) {
        this.schema = schema;
    }

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
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

    public CBCTenant getVendor() {
        return vendor;
    }

    public void setVendor(CBCTenant vendor) {
        this.vendor = vendor;
    }

    public CBCTenant getProvider() {
        return provider;
    }

    public void setProvider(CBCTenant provider) {
        this.provider = provider;
    }

    public String getNormalizedFileUri() {
        return normalizedFileUri;
    }

    public void setNormalizedFileUri(String normalizedFileUri) {
        this.normalizedFileUri = normalizedFileUri;
    }

    public String getProcessedFileUri() {
        return processedFileUri;
    }

    public void setProcessedFileUri(String processedFileUri) {
        this.processedFileUri = processedFileUri;
    }

    public String getAcceptanceNote() {
        return acceptanceNote;
    }

    public void setAcceptanceNote(String acceptanceNote) {
        this.acceptanceNote = acceptanceNote;
    }

    public String getRejectionNote() {
        return rejectionNote;
    }

    public void setRejectionNote(String rejectionNote) {
        this.rejectionNote = rejectionNote;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public CBCUsageStats getStats() {
        return stats;
    }

    public void setStats(CBCUsageStats stats) {
        this.stats = stats;
    }

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
}

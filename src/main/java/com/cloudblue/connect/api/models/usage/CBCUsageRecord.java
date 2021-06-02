package com.cloudblue.connect.api.models.usage;

import com.cloudblue.connect.api.models.subscription.CBCAsset;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.models.enums.CBCUsageRecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCUsageRecord implements CBCEntity {
    @JsonProperty
    private String id;

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("external_billing_id")
    private String externalBillingId;

    @JsonProperty("external_billing_note")
    private String externalBillingNote;

    @JsonProperty
    private CBCAsset asset;

    @JsonProperty
    private Integer tier;

    @JsonProperty
    private Double amount;

    @JsonProperty
    private CBCItemCategory category;

    @JsonProperty
    private Long multiplier;

    @JsonProperty
    private Double usage;

    @JsonProperty
    private CBCUsageRecordStatus status;

    @JsonProperty
    private CBCUsageReport usageReport;

    @JsonProperty
    private List<CBCUsageRecordParam> params;

    @JsonProperty("closed_at")
    private Date closedAt;

    @JsonProperty("closed_by")
    private String closedBy;

    @JsonProperty("error_codes")
    private String errorCodes;

    @JsonProperty("error_details")
    private String errorDetails;

    // TODO Add item ref after item class is available


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getExternalBillingId() {
        return externalBillingId;
    }

    public void setExternalBillingId(String externalBillingId) {
        this.externalBillingId = externalBillingId;
    }

    public String getExternalBillingNote() {
        return externalBillingNote;
    }

    public void setExternalBillingNote(String externalBillingNote) {
        this.externalBillingNote = externalBillingNote;
    }

    public CBCAsset getAsset() {
        return asset;
    }

    public void setAsset(CBCAsset asset) {
        this.asset = asset;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CBCItemCategory getCategory() {
        return category;
    }

    public void setCategory(CBCItemCategory category) {
        this.category = category;
    }

    public Long getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Long multiplier) {
        this.multiplier = multiplier;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public CBCUsageRecordStatus getStatus() {
        return status;
    }

    public void setStatus(CBCUsageRecordStatus status) {
        this.status = status;
    }

    public CBCUsageReport getUsageReport() {
        return usageReport;
    }

    public void setUsageReport(CBCUsageReport usageReport) {
        this.usageReport = usageReport;
    }

    public List<CBCUsageRecordParam> getParams() {
        return params;
    }

    public void setParams(List<CBCUsageRecordParam> params) {
        this.params = params;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String errorCodes) {
        this.errorCodes = errorCodes;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }
}

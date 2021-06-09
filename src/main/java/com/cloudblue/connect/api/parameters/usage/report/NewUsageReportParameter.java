package com.cloudblue.connect.api.parameters.usage.report;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.models.contract.CBCContract;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.cloudblue.connect.api.models.usage.CBCUsagePeriod;
import com.cloudblue.connect.api.models.usage.CBCUsageReport;
import com.cloudblue.connect.api.parameters.Embeddable;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class NewUsageReportParameter implements Embeddable {
    @Parameter
    @Placement(order = 2)
    private String productId;

    @Parameter
    @Placement(order = 3)
    private String contractId;

    @Parameter
    @Placement(order = 1)
    private String name;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB, order = 2)
    private String description;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB, order = 3)
    private String note;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB, order = 4)
    private String periodFrom;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB, order = 5)
    private String periodTo;

    @Parameter
    @Optional
    @Placement(order = 4)
    private String currency;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB, order = 1)
    private String externalId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
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

    public String getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(String periodFrom) {
        this.periodFrom = periodFrom;
    }

    public String getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(String periodTo) {
        this.periodTo = periodTo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public Object buildEntity() {
        CBCUsageReport report = new CBCUsageReport();

        report.setProduct(new CBCProduct());
        report.getProduct().setId(getProductId());

        report.setContract(new CBCContract());
        report.getContract().setId(getContractId());

        report.setName(getName());
        report.setDescription(getDescription());
        report.setNote(getNote());
        report.setCurrency(getCurrency());
        report.setExternalId(getExternalId());

        if (getPeriodFrom() != null || getPeriodTo() != null) {
            report.setPeriod(new CBCUsagePeriod());
            report.getPeriod().setFrom(getPeriodFrom());
            report.getPeriod().setTo(getPeriodTo());
        }

        return report;
    }
}

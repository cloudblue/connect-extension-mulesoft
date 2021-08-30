/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.usage.report;

import com.cloudblue.connect.api.models.usage.CBCUsagePeriod;
import com.cloudblue.connect.api.models.usage.CBCUsageReport;
import com.cloudblue.connect.api.parameters.Embeddable;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class BaseUsageReportParameter implements Embeddable {
    @Parameter
    @Optional
    private String description;

    @Parameter
    @Optional
    private String note;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB)
    private String periodFrom;

    @Parameter
    @Optional
    @Placement(tab = Placement.ADVANCED_TAB)
    private String periodTo;

    @Parameter
    @Optional
    private String currency;

    @Parameter
    @Optional
    private String externalId;

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

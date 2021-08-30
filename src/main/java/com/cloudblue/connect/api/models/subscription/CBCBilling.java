/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.subscription;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCBilling {
    @JsonProperty
    private CBCBillingPeriod period;

    @JsonProperty("next_date")
    private String nextDate;

    @JsonProperty
    private CBCBillingAnniversary anniversary;

    @JsonProperty
    private CBCBillingStats stats;

    public CBCBillingPeriod getPeriod() {
        return period;
    }

    public void setPeriod(CBCBillingPeriod period) {
        this.period = period;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public CBCBillingStats getStats() {
        return stats;
    }

    public void setStats(CBCBillingStats stats) {
        this.stats = stats;
    }

    public CBCBillingAnniversary getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(CBCBillingAnniversary anniversary) {
        this.anniversary = anniversary;
    }
}

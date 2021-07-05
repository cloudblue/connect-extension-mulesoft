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
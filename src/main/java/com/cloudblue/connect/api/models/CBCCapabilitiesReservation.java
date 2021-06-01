package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCapabilitiesReservation implements CBCEntity {

    @JsonProperty
    private Boolean consumption;

    public Boolean getConsumption() {
        return consumption;
    }

    public void setConsumption(Boolean consumption) {
        this.consumption = consumption;
    }    
}

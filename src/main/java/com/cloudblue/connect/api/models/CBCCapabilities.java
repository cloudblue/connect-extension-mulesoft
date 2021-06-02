package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCapabilities implements CBCEntity {

    @JsonProperty
    private CBCCapabilitiesPpu ppu;

    @JsonProperty
    private CBCCapabilitiesReservation resevation;
    
    @JsonProperty
    private CBCCapabilitiesValidation cart;
    
    @JsonProperty
    private CBCCapabilitiesValidation inquiring;
    

    public CBCCapabilitiesPpu getPpu() {
        return ppu;
    }

    public void setPpu(CBCCapabilitiesPpu ppu) {
        this.ppu = ppu;
    }    

    public CBCCapabilitiesReservation getResevation() {
        return resevation;
    }

    public void setResevation(CBCCapabilitiesReservation resevation) {
        this.resevation = resevation;
    }    


    public CBCCapabilitiesValidation getCart() {
        return cart;
    }

    public void setCart(CBCCapabilitiesValidation cart) {
        this.cart = cart;
    }    


    public CBCCapabilitiesValidation getInquiring() {
        return inquiring;
    }

    public void setInquiring(CBCCapabilitiesValidation inquiring) {
        this.inquiring = inquiring;
    }     
}

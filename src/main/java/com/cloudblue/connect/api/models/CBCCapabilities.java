package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCapabilities implements CBCEntity {

    @JsonProperty
    private CBCPpuCapabilities ppu;

    @JsonProperty
    private CBCReservationCapabilities resevation;
    
    @JsonProperty
    private CBCValidationCapabilities cart;
    
    @JsonProperty
    private CBCValidationCapabilities inquiring;
    

    public CBCPpuCapabilities getPpu() {
        return ppu;
    }

    public void setPpu(CBCPpuCapabilities ppu) {
        this.ppu = ppu;
    }    

    public CBCReservationCapabilities getResevation() {
        return resevation;
    }

    public void setResevation(CBCReservationCapabilities resevation) {
        this.resevation = resevation;
    }    


    public CBCValidationCapabilities getCart() {
        return cart;
    }

    public void setCart(CBCValidationCapabilities cart) {
        this.cart = cart;
    }    


    public CBCValidationCapabilities getInquiring() {
        return inquiring;
    }

    public void setInquiring(CBCValidationCapabilities inquiring) {
        this.inquiring = inquiring;
    }     
}

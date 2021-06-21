package com.cloudblue.connect.api.models.tier;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCContactInfo implements CBCEntity {
    @Parameter
    @JsonProperty("address_line1")
    private String addressLine1;
    
    @Parameter
    @JsonProperty("address_line2")
    private String addressLine2;
    
    @Parameter
    @JsonProperty
    private String city;
    
    @Parameter
    @Optional    
    @JsonProperty
    private String state;
    
    @Parameter
    @JsonProperty("postal_code")
    private String postalCode;
    
    @Parameter
    @JsonProperty
    private String country;
    
    @Parameter
    @JsonProperty
    private CBCContact contact;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CBCContact getContact() {
        return contact;
    }

    public void setContact(CBCContact contact) {
        this.contact = contact;
    }
    
    
}

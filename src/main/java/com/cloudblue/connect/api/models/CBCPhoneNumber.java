package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCPhoneNumber implements CBCEntity {
    @Parameter
    @JsonProperty("area_code")
    private String areaCode;
    
    @Parameter
    @JsonProperty("country_code")
    private String countryCode;
    
    @Parameter
    @JsonProperty("extension")
    private String extension;

    @Parameter
    @JsonProperty ("phone_number")
    private String phoneNumber;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }    
}

package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCContact implements CBCEntity {
    @Parameter
    @JsonProperty("first_name")
    private String firstName;
    
    @Parameter
    @JsonProperty("last_name")
    private String lastName;
    
    @Parameter
    @JsonProperty
    private String email;

    @Parameter
    @Optional
    @JsonProperty("phone_number")
    private CBCPhoneNumber phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CBCPhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(CBCPhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
   
}

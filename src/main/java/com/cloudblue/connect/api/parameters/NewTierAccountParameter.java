package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.*;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewTierAccountParameter implements Embeddable {
    
    @Parameter
    @Optional
    private String id;
    
    @Parameter
    private String type;
   
    @Parameter
    private String externalId;
    
    @Parameter
    @Optional
    private String externalUid = UUID.randomUUID().toString();

    @Parameter
    @Optional
    private String parentId;
    
    @Parameter
    private String name;

    @Parameter
    private CBCContactInfo contactInfo;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalUid() {
        if (externalUid == null || externalUid.isEmpty())
            externalUid = UUID.randomUUID().toString();
        return externalUid;
    }

    public void setExternalUid(String externalUid) {
        this.externalUid = externalUid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CBCContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(CBCContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {

        CBCAccount tierAccount = new CBCAccount();
        if (this.id != null){
            tierAccount.setId(this.id);
        }
        tierAccount.setType(this.type);
        if (this.externalId != null){
            tierAccount.setExternalId(this.externalId);
        }
        if (this.externalUid != null){
            tierAccount.setExternalUid(this.externalUid);
        }
        if (this.parentId != null){
            tierAccount.getParent().setId(this.parentId);
        }    
        tierAccount.setCompanyName(this.name);
        tierAccount.setContactInfo(new CBCContactInfo());
        tierAccount.getContactInfo().setAddressLine1(this.contactInfo.getAddressLine1());
        tierAccount.getContactInfo().setAddressLine2(this.contactInfo.getAddressLine2());
        tierAccount.getContactInfo().setCity(this.contactInfo.getCity());
        tierAccount.getContactInfo().setState(this.contactInfo.getState());
        tierAccount.getContactInfo().setPostalCode(this.contactInfo.getPostalCode());
        tierAccount.getContactInfo().setCountry(this.contactInfo.getCountry());
        tierAccount.getContactInfo().setContact(new CBCContact()); 
        tierAccount.getContactInfo().getContact().setFirstName(this.getContactInfo().getContact().getFirstName());  
        tierAccount.getContactInfo().getContact().setLastName(this.getContactInfo().getContact().getLastName());  
        tierAccount.getContactInfo().getContact().setEmail(this.getContactInfo().getContact().getEmail());  
        tierAccount.getContactInfo().getContact().setPhoneNumber(new CBCPhoneNumber());
        tierAccount.getContactInfo().getContact().getPhoneNumber().setAreaCode(this.getContactInfo().getContact().getPhoneNumber().getAreaCode());     
        tierAccount.getContactInfo().getContact().getPhoneNumber().setCountryCode(this.getContactInfo().getContact().getPhoneNumber().getCountryCode());
        tierAccount.getContactInfo().getContact().getPhoneNumber().setExtension(this.getContactInfo().getContact().getPhoneNumber().getExtension());
        tierAccount.getContactInfo().getContact().getPhoneNumber().setPhoneNumber(this.getContactInfo().getContact().getPhoneNumber().getPhoneNumber());

        return tierAccount;    
    }
}

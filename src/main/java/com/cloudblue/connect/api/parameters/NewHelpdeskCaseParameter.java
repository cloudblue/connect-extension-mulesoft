package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.*;
import com.cloudblue.connect.api.models.enums.CBCCaseType;

import java.util.List;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewHelpdeskCaseParameter implements Embeddable {
    
    @Parameter
    private String productId;
    
    @Parameter
    private String subject;
    
    @Parameter
    @Optional
    private String description;
    
    @Parameter
    private String priority;
    
    @Parameter
    private CBCCaseType type;
    
    @Parameter
    private String receiverAccount;
    
    @Parameter
    private String issuerRecipient;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public CBCCaseType getType() {
        return type;
    }

    public void setType(CBCCaseType type) {
        this.type = type;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getIssuerRecipient() {
        return issuerRecipient;
    }

    public void setIssuerRecipient(String issuerRecipient) {
        this.issuerRecipient = issuerRecipient;
    }

    @Override
    @JsonIgnore
    public Object buildEntity() {
        
        CBCCase request = new CBCCase();
        request.setProduct(new CBCProduct());
        request.getProduct().setId(this.productId);
        request.setSubject(this.subject);
        request.setDescription(this.description);
        request.setPriority(this.priority);
        request.setType(this.type);
        request.setReceiver(new CBCCaseIssuer());
        request.getReceiver().setAccount(new CBCBy());
        request.getReceiver().getAccount().setId(this.receiverAccount);

        request.setIssuer(new CBCCaseIssuer());
        List<CBCBy> recipients = new ArrayList<CBCBy>();
        CBCBy element = new CBCBy();
        recipients.add(element);
        element.setId(this.issuerRecipient);
        request.getIssuer().setRecipients(recipients);

        return request;
    }
}

package com.cloudblue.connect.api.models.ticketing;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.enums.CBCCaseState;
import com.cloudblue.connect.api.models.enums.CBCCaseType;
import com.cloudblue.connect.api.models.product.CBCProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCase {
    @JsonProperty
    private String id;

    @JsonProperty
    private CBCProduct product;

    @JsonProperty
    private String subject;

    @JsonProperty
    private String description;

    @JsonProperty
    private String priority;

    @JsonProperty
    private CBCCaseState state;

    @JsonProperty
    private CBCCaseType type;

    @JsonProperty
    private CBCEvents events;

    @JsonProperty
    private CBCCaseIssuer issuer;  

    @JsonProperty
    private CBCCaseIssuer receiver;  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
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

    public CBCCaseState getState() {
        return state;
    }

    public void setState(CBCCaseState state) {
        this.state = state;
    }

    public CBCCaseType getType() {
        return type;
    }

    public void setType(CBCCaseType type) {
        this.type = type;
    }    

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    }
    
    public CBCCaseIssuer getIssuer() {
        return issuer;
    }

    public void setIssuer(CBCCaseIssuer issuer) {
        this.issuer = issuer;
    }

    public CBCCaseIssuer getReceiver() {
        return receiver;
    }

    public void setReceiver(CBCCaseIssuer receiver) {
        this.receiver = receiver;
    }
}

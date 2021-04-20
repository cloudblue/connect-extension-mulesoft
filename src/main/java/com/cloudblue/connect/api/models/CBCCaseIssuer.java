package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCCaseIssuer implements CBCEntity {

    @JsonProperty
    private CBCBy account;    

    @JsonProperty
    private CBCBy agent;    

    @JsonProperty
    private List<CBCBy> recipients;    

    
    public CBCBy getAccount() {
        return account;
    }

    public void setAccount(CBCBy account) {
        this.account = account;
    }

    public CBCBy getAgent() {
        return agent;
    }

    public void setAgent(CBCBy agent) {
        this.agent = agent;
    }    
    public List<CBCBy> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<CBCBy> recipients) {
        this.recipients = recipients;
    }    
}

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
    private CBCUser account;    

    @JsonProperty
    private CBCUser agent;    

    @JsonProperty
    private List<CBCUser> recipients;    

    
    public CBCUser getAccount() {
        return account;
    }

    public void setAccount(CBCUser account) {
        this.account = account;
    }

    public CBCUser getAgent() {
        return agent;
    }

    public void setAgent(CBCUser agent) {
        this.agent = agent;
    }    
    public List<CBCUser> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<CBCUser> recipients) {
        this.recipients = recipients;
    }    
}

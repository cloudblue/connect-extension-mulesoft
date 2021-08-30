/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.models.ticketing;

import com.cloudblue.connect.api.models.common.CBCUser;
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

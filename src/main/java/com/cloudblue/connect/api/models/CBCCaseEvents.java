package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCCaseEvents implements CBCEntity {

    @JsonProperty
    private CBCDetail closed;   

    @JsonProperty
    private CBCDetail created;   

    @JsonProperty
    private CBCDetail inquiring;   

    @JsonProperty
    private CBCDetail pending;   

    @JsonProperty
    private CBCDetail resolved;   

    @JsonProperty
    private CBCDetail updated;   

    public CBCDetail getClosed() {
        return closed;
    }

    public void setClosed(CBCDetail closed) {
        this.closed = closed;
    }  

    public CBCDetail getCreated() {
        return created;
    }

    public void setCreated(CBCDetail created) {
        this.created = created;
    }  

    public CBCDetail getInquiring() {
        return inquiring;
    }

    public void setInquiring(CBCDetail inquiring) {
        this.inquiring = inquiring;
    }  

    public CBCDetail getPending() {
        return pending;
    }

    public void setPending(CBCDetail pending) {
        this.pending = pending;
    }  

    public CBCDetail getResolved() {
        return resolved;
    }

    public void setResolved(CBCDetail resolved) {
        this.resolved = resolved;
    }  
}

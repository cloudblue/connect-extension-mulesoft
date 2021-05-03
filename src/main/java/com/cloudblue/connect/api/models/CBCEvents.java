package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCEvents implements CBCEntity {

    @JsonProperty
    private CBCEvent closed;   

    @JsonProperty
    private CBCEvent created;   

    @JsonProperty
    private CBCEvent inquiring;   

    @JsonProperty
    private CBCEvent pending;   

    @JsonProperty
    private CBCEvent resolved;   

    @JsonProperty
    private CBCEvent updated;   

    public CBCEvent getClosed() {
        return closed;
    }

    public void setClosed(CBCEvent closed) {
        this.closed = closed;
    }  

    public CBCEvent getCreated() {
        return created;
    }

    public void setCreated(CBCEvent created) {
        this.created = created;
    }  

    public CBCEvent getInquiring() {
        return inquiring;
    }

    public void setInquiring(CBCEvent inquiring) {
        this.inquiring = inquiring;
    }  

    public CBCEvent getPending() {
        return pending;
    }

    public void setPending(CBCEvent pending) {
        this.pending = pending;
    }  

    public CBCEvent getResolved() {
        return resolved;
    }

    public void setResolved(CBCEvent resolved) {
        this.resolved = resolved;
    }  
}

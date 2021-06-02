package com.cloudblue.connect.api.models.common;

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

    @JsonProperty
    private CBCEvent uploaded;

    @JsonProperty
    private CBCEvent submitted;

    @JsonProperty
    private CBCEvent accepted;

    @JsonProperty
    private CBCEvent rejected;

    @JsonProperty
    private CBCEvent deleted;

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

    public CBCEvent getUpdated() {
        return updated;
    }

    public void setUpdated(CBCEvent updated) {
        this.updated = updated;
    }

    public CBCEvent getUploaded() {
        return uploaded;
    }

    public void setUploaded(CBCEvent uploaded) {
        this.uploaded = uploaded;
    }

    public CBCEvent getSubmitted() {
        return submitted;
    }

    public void setSubmitted(CBCEvent submitted) {
        this.submitted = submitted;
    }

    public CBCEvent getAccepted() {
        return accepted;
    }

    public void setAccepted(CBCEvent accepted) {
        this.accepted = accepted;
    }

    public CBCEvent getRejected() {
        return rejected;
    }

    public void setRejected(CBCEvent rejected) {
        this.rejected = rejected;
    }

    public CBCEvent getDeleted() {
        return deleted;
    }

    public void setDeleted(CBCEvent deleted) {
        this.deleted = deleted;
    }
}

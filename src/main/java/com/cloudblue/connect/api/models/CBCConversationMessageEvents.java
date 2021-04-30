package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCConversationMessageEvents implements CBCEntity {

    @JsonProperty
    private CBCEvent created;   

    @JsonProperty
    private CBCEvent updated;   

    public CBCEvent getCreated() {
        return created;
    }

    public void setCreated(CBCEvent created) {
        this.created = created;
    }  

    public CBCEvent getUpdated() {
        return updated;
    }

    public void setUpdated(CBCEvent updated) {
        this.updated = updated;
    }  
}

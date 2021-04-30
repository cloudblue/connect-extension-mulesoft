package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCConversationMessageEvents implements CBCEntity {

    @JsonProperty
    private CBCEvents created;   

    @JsonProperty
    private CBCEvents updated;   

    public CBCEvents getCreated() {
        return created;
    }

    public void setCreated(CBCEvents created) {
        this.created = created;
    }  

    public CBCEvents getUpdated() {
        return updated;
    }

    public void setUpdated(CBCEvents updated) {
        this.updated = updated;
    }  
}

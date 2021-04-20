package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CBCConversationMessageEvents implements CBCEntity {

    @JsonProperty
    private CBCDetail created;   

    @JsonProperty
    private CBCDetail updated;   

    public CBCDetail getCreated() {
        return created;
    }

    public void setCreated(CBCDetail created) {
        this.created = created;
    }  

    public CBCDetail getUpdated() {
        return updated;
    }

    public void setUpdated(CBCDetail updated) {
        this.updated = updated;
    }  
}

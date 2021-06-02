package com.cloudblue.connect.api.models.marketplace;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCHubBinding {

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty
    private CBCHub hub;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public CBCHub getHub() {
        return hub;
    }

    public void setHub(CBCHub hub) {
        this.hub = hub;
    }
}

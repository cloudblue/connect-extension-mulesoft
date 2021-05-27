package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCLinks implements CBCEntity {

    @JsonProperty
    private String title;

    @JsonProperty
    private String url;

    @JsonProperty
    private String visibleFor;
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    

    public String getVisibleFor() {
        return visibleFor;
    }

    public void setVisibleFor(String visibleFor) {
        this.visibleFor = visibleFor;
    }    

}

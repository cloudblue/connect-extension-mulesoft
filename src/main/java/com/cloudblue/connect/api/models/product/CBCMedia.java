package com.cloudblue.connect.api.models.product;

import com.cloudblue.connect.api.models.common.CBCEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCMedia implements CBCEntity {

    @JsonProperty
    private String id;

    @JsonProperty
    private String type;

    @JsonProperty
    private String thumbnail;

    @JsonProperty
    private String url;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }    

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
}

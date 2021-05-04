package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.models.enums.CBCWebhookEventType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCWebhook {

    @JsonProperty
    private String id;

    @JsonProperty
    @Parameter
    private String label;

    @JsonProperty("product_id")
    @Parameter
    private String productId;

    @JsonProperty("external_url")
    @Parameter
    private String externalUrl;

    @JsonProperty("jwt_secret")
    @Parameter
    private String jwtToken;

    @JsonProperty
    @Parameter
    private String description;

    @JsonProperty
    @Parameter
    private Boolean active;

    @JsonProperty
    private CBCProduct product;

    @JsonProperty
    private String type = "event";

    @JsonProperty("http_method")
    private String httpMethod = "POST";

    @JsonProperty("object_class")
    @Parameter
    private CBCWebhookEventType objectClass;

    @JsonProperty
    private Map<String,String> data = new HashMap<>();

    @JsonProperty
    private Map<String, String> headers = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CBCProduct getProduct() {
        return product;
    }

    public void setProduct(CBCProduct product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CBCWebhookEventType getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(CBCWebhookEventType objectClass) {
        this.objectClass = objectClass;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}

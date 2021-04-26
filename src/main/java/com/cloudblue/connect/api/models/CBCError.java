package com.cloudblue.connect.api.models;

import com.cloudblue.connect.api.clients.constants.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCError {
    @JsonProperty("error_code")
    private String errorCode;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("params")
    private Map<String, Object> params;

    @JsonIgnore
    private HttpStatus status;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String errorDetail() {
        StringBuilder builder = new StringBuilder();
        if (this.errors!=null && !this.errors.isEmpty()){
            for (String error : errors){
                builder.append(error).append(",");
            }
            if (builder.length()>1)
                builder.deleteCharAt(builder.length()-1);

        }
        return builder.toString();
    }


    private String extractErrors(){
        return "[" + this.errorDetail() + "]";
    }

    private String extractParams(){
        StringBuilder builder = new StringBuilder("{");
        if (this.params!=null && !this.params.isEmpty()){
            for (Map.Entry<String, Object> entry : params.entrySet()){
               builder.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
            }
            if(builder.length() >1)
                builder.deleteCharAt(builder.length()-1);
        }
        builder.append("}");
        return builder.toString();
    }
    @Override
    public String toString() {
        return "Error{" +
                "errorCode='" + errorCode + '\'' +
                ", errors=" + extractErrors() +
                ", params=" + extractParams() +
                ", status=" + status.value() +
                '}';
    }
}

package com.cloudblue.connect.api.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CBCConversationMessages {
    @JsonProperty
    private String id;

    @JsonProperty
    private String conversation;

    @JsonProperty
    private CBCBy account;

    @JsonProperty
    private String created;

    @JsonProperty
    private CBCBy creator;

    @JsonProperty
    private String text;

    @JsonProperty
    private String type;

    @JsonProperty
    private CBCConversationMessageEvents events;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }    

    public CBCBy getAccount() {
        return account;
    }

    public void setAccount(CBCBy account) {
        this.account = account;
    }    

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    } 

    public CBCBy getCreator() {
        return creator;
    }

    public void setCreator(CBCBy creator) {
        this.creator = creator;
    }     

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    } 

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    } 

    public CBCConversationMessageEvents getEvents() {
        return events;
    }

    public void setEvents(CBCConversationMessageEvents events) {
        this.events = events;
    } 
}

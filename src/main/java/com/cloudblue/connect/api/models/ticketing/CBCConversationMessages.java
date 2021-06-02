package com.cloudblue.connect.api.models.ticketing;
import com.cloudblue.connect.api.models.common.CBCEvents;
import com.cloudblue.connect.api.models.common.CBCUser;
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
    private CBCUser account;

    @JsonProperty
    private String created;

    @JsonProperty
    private CBCUser creator;

    @JsonProperty
    private String text;

    @JsonProperty
    private String type;

    @JsonProperty
    private CBCEvents events;

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

    public CBCUser getAccount() {
        return account;
    }

    public void setAccount(CBCUser account) {
        this.account = account;
    }    

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    } 

    public CBCUser getCreator() {
        return creator;
    }

    public void setCreator(CBCUser creator) {
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

    public CBCEvents getEvents() {
        return events;
    }

    public void setEvents(CBCEvents events) {
        this.events = events;
    } 
}

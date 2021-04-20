package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.*;

import java.util.List;
import java.util.ArrayList;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.internal.MuleDsqlParser.identifier_return;

public class NewConversationMessage implements Embeddable {

    @Parameter
    private String conversationId;
    
    @Parameter
    private CBCBy account;
    
    @Parameter
    private CBCBy creator;
    
    @Parameter
    private String text;
    
    @Parameter
    private String type;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public CBCBy getAccount() {
        return account;
    }

    public void setAccount(CBCBy account) {
        this.account = account;
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

    @Override
    @JsonIgnore
    public Object buildEntity() {
        
        CBCConversationMessages request = new CBCConversationMessages();
        request.setAccount(new CBCBy());
        if (this.account != null){
            request.getAccount().setId(this.account.getId());
        }
        request.setCreator(new CBCBy());
        if (this.creator != null){
            request.getCreator().setId(this.creator.getId());
        }    
        request.setText(this.text);
        request.setType(this.type);

        return request;
    }
}

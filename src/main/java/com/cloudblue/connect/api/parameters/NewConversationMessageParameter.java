package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.common.CBCUser;
import com.cloudblue.connect.api.models.ticketing.CBCConversationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NewConversationMessageParameter implements Embeddable {

    @Parameter
    private String conversationId;
    
    @Parameter
    private CBCUser account;
    
    @Parameter
    private CBCUser creator;
    
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

    public CBCUser getAccount() {
        return account;
    }

    public void setAccount(CBCUser account) {
        this.account = account;
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

    @Override
    @JsonIgnore
    public Object buildEntity() {
        
        CBCConversationMessages request = new CBCConversationMessages();
        if (this.account != null){
            request.setAccount(new CBCUser());
            request.getAccount().setId(this.account.getId());
        }
        if (this.creator != null){
            request.setCreator(new CBCUser());
            request.getCreator().setId(this.creator.getId());
        }    
        request.setText(this.text);
        request.setType(this.type);

        return request;
    }
}

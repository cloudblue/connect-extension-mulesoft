package com.cloudblue.connect.api.parameters;

import com.cloudblue.connect.api.models.ticketing.CBCCase;
import com.cloudblue.connect.api.models.enums.CBCCaseState;
import com.cloudblue.connect.api.models.ticketing.CBCConversationMessages;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class ChangeStatusHelpdeskCaseParameter implements Embeddable {

    @Parameter
    private String caseId;

    @Parameter
    private CBCCaseState operation;

    @Parameter
    private String comment;

    
    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public CBCCaseState getOperation() {
        return operation;
    }

    public void setOperation(CBCCaseState operation) {
        this.operation = operation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public Object buildEntity() {

        CBCCase helpdeskCase = new CBCCase();
        helpdeskCase.setId(this.caseId);
        helpdeskCase.setState(this.operation);

        CBCConversationMessages message = new CBCConversationMessages();
        message.setConversation(this.caseId);
        message.setText(this.comment);
        return (helpdeskCase);
    }

}

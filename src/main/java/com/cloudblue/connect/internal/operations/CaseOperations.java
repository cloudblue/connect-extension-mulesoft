package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.ctc.wstx.io.EBCDICCodec;
import com.cloudblue.connect.api.models.CBCCase;
import com.cloudblue.connect.api.models.CBCConversationMessages;
import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.parameters.NewConversationMessage;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.clients.utils.URLBuilder;
import com.cloudblue.connect.api.parameters.NewHelpdeskCaseParameter;
import com.cloudblue.connect.api.parameters.ChangeStatusHelpdeskCaseParameter;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.weave.v2.grammar.NotEqOpId;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CaseOperations {
    
    private final Logger LOGGER = LoggerFactory.getLogger(CaseOperations.class);
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Helpdesk Case")
    public CBCCase getCase(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Case ID") ResourceActionParameter getCaseParameter
    ) throws CBCException {

        URLBuilder urlBuilder = new URLBuilder()
                .addCollection("helpdesk/cases", getCaseParameter.getId());

        return connection.exchange(
                urlBuilder.build(),
                null,
                HttpMethod.GET,
                null,
                CBCCase.class
        );
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Helpdesk Case")
    public CBCCase createHelpdeskCase(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Helpdesk Case Details")
                    NewHelpdeskCaseParameter newRequestParameter
    ) throws CBCException {
        //return this.createCase(connection, newRequestParameter);
        return (CBCCase) connection.newQ(new TypeReference<CBCCase>() {})
        .collection("helpdesk/cases")
        .create(newRequestParameter.buildEntity());
    }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Change Helpdesk Case State")
    public CBCCase changeStatusHelpdeskCase(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Case ID") 
                    ChangeStatusHelpdeskCaseParameter newRequestParameter
    ) throws CBCException {
        String operation =  newRequestParameter.getOperation().name();
        switch (operation) 
        {
            case "inquiring":  operation = "inquire";
                     break;
            case "pending":  operation = "pend";
                     break;
            case "resolved":  operation = "resolve";
                     break;
            case "closed":  operation = "close";
        }
        CBCCase result = (CBCCase)connection.newQ(new TypeReference<CBCCase>() {})
            .collection("helpdesk/cases", newRequestParameter.getCaseId())
            .action(operation, HttpMethod.POST);
        
        NewConversationMessage message = new NewConversationMessage();
        message.setText(newRequestParameter.getComment());
        message.setConversationId(newRequestParameter.getCaseId());
        message.setType("message");
        ConversationMessageOperations messageOperation  = new ConversationMessageOperations();
        messageOperation.createConversationMessage(connection, message);
        return result;
    }    
}

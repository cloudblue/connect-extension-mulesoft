package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCCase;
import com.cloudblue.connect.api.parameters.NewConversationMessage;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.parameters.NewHelpdeskCaseParameter;
import com.cloudblue.connect.api.parameters.ChangeStatusHelpdeskCaseParameter;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;


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
        return (CBCCase) connection
                .newQ(new TypeReference<CBCCase>() {})
                .collection("helpdesk").collection("cases", getCaseParameter.getId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Helpdesk Case")
    public CBCCase createHelpdeskCase(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Helpdesk Case Details")
                    NewHelpdeskCaseParameter newRequestParameter
    ) throws CBCException {
        return (CBCCase) connection.newQ(new TypeReference<CBCCase>() {})
        .collection("helpdesk").collection("cases")
        .create(newRequestParameter.buildEntity());
    }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Change Helpdesk Case State")
    public CBCCase changeStatusHelpdeskCase(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Case ID") 
                    ChangeStatusHelpdeskCaseParameter newRequestParameter
    ) throws CBCException {
        String operation =  newRequestParameter.getOperation().getOperation();
        
        CBCCase result = (CBCCase)connection.newQ(new TypeReference<CBCCase>() {})
            .collection("helpdesk").collection("cases", newRequestParameter.getCaseId())
            .collection(operation)
            .create(newRequestParameter.buildEntity());


        NewConversationMessage message = new NewConversationMessage();
        message.setText(newRequestParameter.getComment());
        message.setConversationId(newRequestParameter.getCaseId());
        message.setType("message");
        ConversationMessageOperations messageOperation  = new ConversationMessageOperations();
        messageOperation.createConversationMessage(connection, message);
        return result;
    }    
}

package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCConversationMessages;
import com.cloudblue.connect.api.parameters.NewConversationMessage;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConversationMessageOperations {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ConversationMessageOperations.class);

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Message Conversation")
    public CBCConversationMessages createConversationMessage(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Create Message Conversation Details")
            NewConversationMessage newRequestParameter
    ) throws CBCException {
        return (CBCConversationMessages) connection.newQ(new TypeReference<CBCConversationMessages>() {})
        .collection("conversations/"+newRequestParameter.getConversationId()+"/messages")
        .create(newRequestParameter.buildEntity());
    }
}

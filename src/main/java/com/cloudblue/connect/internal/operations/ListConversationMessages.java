package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.ticketing.CBCConversationMessages;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;


public class ListConversationMessages extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Conversation Messages")
    public List<CBCConversationMessages> listConversationMessages(
            @Connection CBCConnection connection,
            @ParameterGroup(name="conversation ID") ResourceActionParameter getCaseParameter
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCConversationMessages>>() {})
                .collection("conversations", getCaseParameter.getId())
                .collection("messages");
        resolve(q);

        return (List<CBCConversationMessages>) q.get();
    }
}

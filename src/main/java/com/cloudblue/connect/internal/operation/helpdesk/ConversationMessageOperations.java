/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.helpdesk;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.model.helpdesks.ConversationMessage;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;


public class ConversationMessageOperations {


    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Message Conversation")
    @OutputJsonType(schema = "ConversationMessage-schema.json")
    public Result<InputStream, CBCResponseAttributes> createConversationMessage(
        @Connection CBCConnection connection,
        String conversationId,
        String message
    ) throws MuleException {
        ConversationMessage conversationMessage = new ConversationMessage();
        conversationMessage.setText(message);

        return connection.newQ()
                .collection("conversations", conversationId)
                .collection("messages")
                .create(conversationMessage);
    }
}

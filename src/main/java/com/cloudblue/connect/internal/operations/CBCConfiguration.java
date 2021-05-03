package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.ListAssetOperation;
import com.cloudblue.connect.internal.operations.ListRequestOperation;
import com.cloudblue.connect.internal.operations.SubscriptionOperations;
import com.cloudblue.connect.internal.operations.CaseOperations;
import com.cloudblue.connect.internal.operations.ConversationMessageOperations;
import com.cloudblue.connect.internal.operations.ListCaseOperation;
import com.cloudblue.connect.internal.operations.ListConversationMessages;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
        SubscriptionOperations.class,
        ListRequestOperation.class,
        ListAssetOperation.class,
        CaseOperations.class,
        ListCaseOperation.class,
        ListConversationMessages.class,
        ConversationMessageOperations.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

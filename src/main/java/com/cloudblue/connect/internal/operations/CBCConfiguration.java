package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.sources.ConnectPollingSource;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
    SubscriptionOperations.class,
    ListRequestOperation.class,
    ListAssetOperation.class,
    CaseOperations.class,
    ListCaseOperation.class,
    ListConversationMessages.class,
    ConversationMessageOperations.class,
    TierOperations.class,
    ListTierOperations.class
})
@ConnectionProviders(CBCConnectionProvider.class)
@Sources(ConnectPollingSource.class)
public class CBCConfiguration {}

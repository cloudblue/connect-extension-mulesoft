package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import org.mule.runtime.extension.api.annotation.Operations;
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
    ListTierOperations.class,
    ListUsageRecordOperations.class,
    ListUsageReportOperations.class,
    UsageRecordOperations.class,
    UsageReportOperations.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

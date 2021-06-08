package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.usage.*;

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
    ListUsageRecordOperations.class,
    ListUsageReportOperations.class,
    UsageRecordOperations.class,
    UsageReportOperations.class,
    TierAccountOperations.class,
    ListTierAccountOperation.class,
    ProductOperations.class,
    ListChunkFileOperation.class,
    ChunkFileOperations.class,
    ListReconciliationOperation.class,
    UsageReconciliationOperations.class,
    ListUsageAggregatesOperation.class,
    ListAssetUsageAggregates.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

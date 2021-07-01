package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.usage.*;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
    SubscriptionOperations.class,
    CaseOperations.class,
    ListConversationMessages.class,
    ConversationMessageOperations.class,
    UsageRecordOperations.class,
    UsageReportOperations.class,
    TierAccountOperations.class,
    ListTierAccountOperation.class,
    ProductOperations.class,
    ChunkFileOperations.class,
    UsageReconciliationOperations.class,
    ListAssetUsageAggregates.class,
    ListResourcesOperation.class,
    GetResourcesOperation.class,
    ListProductOperations.class,
    ProductTemplateOperations.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

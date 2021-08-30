/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.config;

import com.cloudblue.connect.internal.connection.provider.CBCConnectionProvider;
import com.cloudblue.connect.internal.operation.*;
import com.cloudblue.connect.internal.operation.helpdesk.ConversationMessageOperations;
import com.cloudblue.connect.internal.operation.resource.GetResourceOperation;
import com.cloudblue.connect.internal.operation.resource.ListResourcesOperation;
import com.cloudblue.connect.internal.operation.helpdesk.CaseOperations;
import com.cloudblue.connect.internal.operation.resource.ResourceActionOperation;
import com.cloudblue.connect.internal.operation.usage.*;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
        SubscriptionOperations.class,
        CaseOperations.class,
        ConversationMessageOperations.class,
        UsageReportOperations.class,
        TierAccountOperations.class,
        ProductOperations.class,
        ChunkFileOperations.class,
        UsageReconciliationOperations.class,
        ListResourcesOperation.class,
        GetResourceOperation.class,
        ResourceActionOperation.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

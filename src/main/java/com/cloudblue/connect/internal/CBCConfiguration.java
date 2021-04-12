package com.cloudblue.connect.internal;

import com.cloudblue.connect.internal.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.ListAssetOperation;
import com.cloudblue.connect.internal.operations.ListRequestOperation;
import com.cloudblue.connect.internal.operations.SubscriptionOperations;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
        SubscriptionOperations.class,
        ListRequestOperation.class,
        ListAssetOperation.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

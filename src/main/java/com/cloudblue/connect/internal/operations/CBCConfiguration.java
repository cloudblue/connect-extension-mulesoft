package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations({
        SubscriptionOperations.class,
        ListRequestOperation.class,
        ListAssetOperation.class
})
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}
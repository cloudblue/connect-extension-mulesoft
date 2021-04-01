package com.cloudblue.connect.internal;

import com.cloudblue.connect.internal.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.operations.SubscriptionOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

@Operations(SubscriptionOperations.class)
@ConnectionProviders(CBCConnectionProvider.class)
public class CBCConfiguration {}

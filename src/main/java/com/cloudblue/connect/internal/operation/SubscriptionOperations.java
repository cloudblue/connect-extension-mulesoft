/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.InputJsonType;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;


public class SubscriptionOperations {

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Purchase Request")
    @OutputJsonType(schema = "Request-schema.json")
    public Result<InputStream, CBCResponseAttributes> createPurchaseRequest(
            @Connection CBCConnection connection,
            @InputJsonType(schema = "NewPurchaseRequest-schema.json")
                    InputStream newPurchaseRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(REQUESTS)
                .create(newPurchaseRequestParameter);
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Billing Request")
    @OutputJsonType(schema = "Request-schema.json")
    public Result<InputStream, CBCResponseAttributes> createBillingRequest(
            @Connection CBCConnection connection,
            @InputJsonType(schema = "NewBillingRequest-schema.json") InputStream newBillingRequest
    ) throws MuleException {
        return connection.newQ()
                .collection(SUBSCRIPTIONS)
                .collection(REQUESTS)
                .create(newBillingRequest);
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Get Billing Request Attributes")
    public Result<InputStream, CBCResponseAttributes> getBillingRequestAttributes(
            @Connection CBCConnection connection,
            String requestId ) throws MuleException {
        return connection.newQ()
                .collection(SUBSCRIPTIONS)
                .collection(REQUESTS, requestId)
                .collection(ATTRIBUTES)
                .get();
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Suspend/Resume/Cancel Request")
    @OutputJsonType(schema = "Request-schema.json")
    public Result<InputStream, CBCResponseAttributes> createAdminHoldRequest(
            @Connection CBCConnection connection,
            @InputJsonType(schema = "NewAdminHoldRequest-schema.json")
                    InputStream newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(REQUESTS)
                .create(newRequestParameter);
    }
}

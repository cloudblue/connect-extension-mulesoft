/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;


import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.api.parameters.accounts.NewTierAccountParameter;
import com.cloudblue.connect.api.parameters.accounts.UpdateTierAccountParameter;
import com.cloudblue.connect.api.parameters.accounts.NewTierConfigParameter;
import com.cloudblue.connect.api.parameters.accounts.UpdateTierConfigRequestParameter;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class TierAccountOperations {


    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Tier Account")
    @OutputJsonType(schema = "TierAccount-schema.json")
    public Result<InputStream, CBCResponseAttributes> createTierAccount(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create/Update Tier Account")
                    NewTierAccountParameter newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(TIER)
                .collection(TIER_ACCOUNTS)
                .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Update Tier Account")
    @OutputJsonType(schema = "TierAccount-schema.json")
    public Result<InputStream, CBCResponseAttributes> updateTierAccount(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Update Tier Account")
        UpdateTierAccountParameter newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(TIER)
                .collection(TIER_ACCOUNTS,newRequestParameter.getId())
                .update(newRequestParameter.buildEntity());
        }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Tier Account Request")
    @OutputJsonType(schema = "TierAccountRequest-schema.json")
    public Result<InputStream, CBCResponseAttributes> createTierAccountRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Tier Account Request")
                    NewTierAccountParameter newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(ACCOUNT_REQUESTS)
                .create(newRequestParameter.buildEntity());
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Tier Config Request")
    @OutputJsonType(schema = "TierConfigRequest-schema.json")
    public Result<InputStream, CBCResponseAttributes>  createTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Create Tier Config Request")
        NewTierConfigParameter newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(TIER)
                .collection(CONFIG_REQUESTS)
                .create(newRequestParameter.buildEntity());
        }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Update Tier Config Request")
    @OutputJsonType(schema = "TierConfigRequest-schema.json")
    public Result<InputStream, CBCResponseAttributes> updateTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Update Tier Account Request")
        UpdateTierConfigRequestParameter newRequestParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(TIER)
            .collection(CONFIG_REQUESTS, newRequestParameter.getTierConfigRequestId())
            .update(newRequestParameter.buildEntity());
        }  
}



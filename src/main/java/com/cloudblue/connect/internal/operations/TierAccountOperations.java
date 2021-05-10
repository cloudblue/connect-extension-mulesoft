package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCAccount;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.parameters.NewTierAccountParameter;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;


import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TierAccountOperations {
    
    private final Logger LOGGER = LoggerFactory.getLogger(CaseOperations.class);
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Tier Account")
    public CBCAccount getTierAccount(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Case ID") ResourceActionParameter getTierAccountParameter
    ) throws CBCException {
        return (CBCAccount) connection
            .newQ(new TypeReference<CBCAccount>() {})
            .collection("tier").collection("accounts", getTierAccountParameter.getId())
            .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create/Update Tier Account")
    public CBCAccount createTierAccount(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create/Update Tier Account")
                    NewTierAccountParameter newRequestParameter
    ) throws CBCException {
        return (CBCAccount) connection.newQ(new TypeReference<CBCAccount>() {})
            .collection("tier").collection("accounts")
            .create(newRequestParameter.buildEntity());
    }    
}

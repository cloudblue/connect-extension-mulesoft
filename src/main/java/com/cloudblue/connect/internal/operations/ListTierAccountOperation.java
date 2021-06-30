package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.tier.CBCAccount;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

public class ListTierAccountOperation extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Tier Account Versions")
    public List<CBCAccount> listTierAccountVersions(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Tier Account ID")
            ResourceActionParameter tierAccountParameter
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCAccount>>() {})
                .collection(TIER).collection(ACCOUNTS, tierAccountParameter.getId())
                .collection(VERSIONS);
        resolve(q);
        return (List<CBCAccount>) q.get();
    }
}

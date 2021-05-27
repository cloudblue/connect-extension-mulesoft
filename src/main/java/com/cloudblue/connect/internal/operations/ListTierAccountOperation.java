package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCAccount;
import com.cloudblue.connect.api.models.CBCAccountRequest;
import com.cloudblue.connect.api.models.CBCTierConfig;
import com.cloudblue.connect.api.models.CBCTierConfigRequest;


import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

public class ListTierAccountOperation extends BaseListOperation {

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Tier Accounts")
    public List<CBCAccount> listTierAccounts(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCAccount>>() {})
                .collection("tier").collection("accounts");
        resolve(q);
        return (List<CBCAccount>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Tier Account Requests")
    public List<CBCAccountRequest> listTierAccountRequests(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCAccountRequest>>() {})
                .collection("tier").collection("account-requests");
        resolve(q);
        return (List<CBCAccountRequest>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Tier Configs")
    public List<CBCTierConfig> listTierConfigs(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCTierConfig>>() {})
                .collection("tier").collection("configs");
        resolve(q);
        return (List<CBCTierConfig>) q.get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Tier Config Requests")
    public List<CBCTierConfigRequest> listTierConfigRequests(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCTierConfigRequest>>() {})
                .collection("tier").collection("config-requests");
        resolve(q);
        return (List<CBCTierConfigRequest>) q.get();
    }    

}

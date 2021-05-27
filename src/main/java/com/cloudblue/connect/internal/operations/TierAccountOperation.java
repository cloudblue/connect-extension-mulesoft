package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCAccount;
import com.cloudblue.connect.api.models.CBCAccountRequest;
import com.cloudblue.connect.api.models.CBCTierConfig;
import com.cloudblue.connect.api.models.CBCTierConfigRequest;
import com.cloudblue.connect.api.parameters.accounts.IgnoreTierAccountRequestParameter;
import com.cloudblue.connect.api.parameters.accounts.NewTierAccountParameter;
import com.cloudblue.connect.api.parameters.accounts.NewTierConfigParameter;
import com.cloudblue.connect.api.parameters.accounts.ApproveTierConfigRequestParameter;
import com.cloudblue.connect.api.parameters.accounts.UpdateTierConfigRequestParameter;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;


import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class TierAccountOperation {
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Tier Account")
    public CBCAccount getTierAccount(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Tier Account ID") ResourceActionParameter getTierAccountParameter
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
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Tier Account Request")
    public CBCAccountRequest getTierAccountRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Tier Account Request ID") ResourceActionParameter getTierAccountParameter
    ) throws CBCException {
        return (CBCAccountRequest) connection
            .newQ(new TypeReference<CBCAccountRequest>() {})
            .collection("tier").collection("account-requests", getTierAccountParameter.getId())
            .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Tier Account Request")
    public CBCAccountRequest createTierAccountRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Tier Account Request")
                    NewTierAccountParameter newRequestParameter
    ) throws CBCException {
        return (CBCAccountRequest) connection.newQ(new TypeReference<CBCAccountRequest>() {})
            .collection("tier").collection("account-requests")
            .create(newRequestParameter.buildEntity());
    }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Accept Tier Account Request")
    public CBCAccountRequest acceptTierAccountRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Accept Tier Account Request")
                    ResourceActionParameter newRequestParameter
    ) throws CBCException {
        return (CBCAccountRequest) connection.newQ(new TypeReference<CBCAccountRequest>() {})
            .collection("tier")
            .collection("account-requests",newRequestParameter.getId())
            .action("accept", HttpMethod.POST,null);
        }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Ignore Tier Account Request")
    public CBCAccountRequest ignoreTierAccountRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Ignore Tier Account Request")
        IgnoreTierAccountRequestParameter newRequestParameter
    ) throws CBCException {
        return (CBCAccountRequest) connection.newQ(new TypeReference<CBCAccountRequest>() {})
            .collection("tier")
            .collection("account-requests",newRequestParameter.getTierAccountRequestId())
            .action("ignore", HttpMethod.POST,newRequestParameter.getReason());
        }           

    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Tier Config")
    public CBCTierConfig getTierConfig(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Tier Config ID") ResourceActionParameter getTierConfigParameter
    ) throws CBCException {
        return (CBCTierConfig) connection
            .newQ(new TypeReference<CBCTierConfig>() {})
            .collection("tier").collection("configs", getTierConfigParameter.getId())
            .get();
        }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Tier Config Request")
    public CBCTierConfigRequest getTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Tier Config Request ID") ResourceActionParameter getTierConfigParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection
            .newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier").collection("config-requests", getTierConfigParameter.getId())
            .get();
        }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Tier Config Request")
    public CBCTierConfigRequest  createTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Create Tier Config Request")
        NewTierConfigParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier").collection("config-requests")
            .create(newRequestParameter.buildEntity());
        }    

    @MediaType(value = ANY, strict = false)
    @DisplayName("Approve Tier Config Request")
    public CBCTierConfigRequest approveTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Approve Tier Config Request")
        ApproveTierConfigRequestParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier")
            .collection("config-requests",newRequestParameter.getTierConfigRequestId())
            .collection("approve")
            .create(newRequestParameter.buildEntity());
        }    
        
    @MediaType(value = ANY, strict = false)
    @DisplayName("Inquire Tier Config Request")
    public CBCTierConfigRequest inquireTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Inquire Tier Account Request")
        ResourceActionParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier")
            .collection("config-requests",newRequestParameter.getId())
            .action("inquire", HttpMethod.POST,null);
        }  

    @MediaType(value = ANY, strict = false)
    @DisplayName("Set Pending Tier Config Request")
    public CBCTierConfigRequest pendingTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Set Pending Tier Account Request")
        ResourceActionParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier")
            .collection("config-requests",newRequestParameter.getId())
            .action("pending", HttpMethod.POST,"");
        }  

    @MediaType(value = ANY, strict = false)
    @DisplayName("Set Fail Tier Config Request")
    public CBCTierConfigRequest failTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Set Fail Tier Account Request")
        ResourceActionParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier")
            .collection("config-requests",newRequestParameter.getId())
            .action("fail", HttpMethod.POST,"");
        }  

    @MediaType(value = ANY, strict = false)
    @DisplayName("Update Tier Config Request")
    public CBCTierConfigRequest updateTierConfigRequest(
        @Connection CBCConnection connection,
        @ParameterGroup(name="Update Tier Account Request")
        UpdateTierConfigRequestParameter newRequestParameter
    ) throws CBCException {
        return (CBCTierConfigRequest) connection.newQ(new TypeReference<CBCTierConfigRequest>() {})
            .collection("tier")
            .collection("config-requests",newRequestParameter.getTierConfigRequestId())
            .update(newRequestParameter.buildEntity());
        }  
}



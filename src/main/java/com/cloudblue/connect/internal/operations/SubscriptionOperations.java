package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.api.parameters.AdminHoldRequestParameter;
import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.parameters.NewPurchaseRequestParameter;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.internal.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SubscriptionOperations {
    
    private final Logger LOGGER = LoggerFactory.getLogger(SubscriptionOperations.class);
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Request")
    public CBCRequest getRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Request ID") ResourceActionParameter getRequestParameter
    ) throws CBCException {

        return (CBCRequest) connection
                .newQ(new TypeReference<CBCRequest>() {})
                .collection("requests", getRequestParameter.getId())
                .get();
    }
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Asset")
    public CBCAsset getAsset(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Asset ID")  ResourceActionParameter getAssetParameter
    ) throws CBCException {

        return (CBCAsset) connection
                .newQ(new TypeReference<CBCAsset>() {})
                .collection("assets", getAssetParameter.getId())
                .get();
    }

    private CBCRequest createRequest(
            CBCConnection connection, Embeddable embeddable
    ) throws CBCException {

        return (CBCRequest) connection.newQ(new TypeReference<CBCRequest>() {})
                .collection("requests")
                .create(embeddable.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Purchase Request")
    public CBCRequest createPurchaseRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Purchase Request Details")
                    NewPurchaseRequestParameter newRequestParameter
    ) throws CBCException {
        return this.createRequest(connection, newRequestParameter);
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Suspend/Resume/Cancel Request")
    public CBCRequest createAdminHoldRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Suspend/Resume/Cancel Request Details")
                    AdminHoldRequestParameter newRequestParameter
    ) throws CBCException {
        return this.createRequest(connection, newRequestParameter);
    }
}

package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.parameters.AdminHoldRequestParameter;
import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.models.subscription.CBCRequest;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.parameters.NewPurchaseRequestParameter;
import com.cloudblue.connect.api.parameters.requests.RequestAction;
import com.cloudblue.connect.api.parameters.requests.UpdateRequestParameter;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;


public class SubscriptionOperations {

    private CBCRequest createRequest(
            CBCConnection connection, Embeddable embeddable
    ) throws CBCException {

        return (CBCRequest) connection.newQ(new TypeReference<CBCRequest>() {})
                .collection(REQUESTS)
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

    @MediaType(value = ANY, strict = false)
    @DisplayName("Request Action")
    public CBCRequest performRequestAction(
            @Connection CBCConnection connection,
            RequestAction requestAction
    ) throws CBCException {
        return (CBCRequest) connection.newQ(new TypeReference<CBCRequest>() {})
                .collection(REQUESTS, requestAction.getId())
                .action(requestAction.action(), HttpMethod.POST, requestAction.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Update Request")
    public CBCRequest updateRequest(
            @Connection CBCConnection connection,
            UpdateRequestParameter requestParameter
    ) throws CBCException {
        return (CBCRequest) connection.newQ(new TypeReference<CBCRequest>() {})
                .collection(REQUESTS, requestParameter.getId())
                .update(requestParameter.buildEntity());
    }
}

package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.models.CBCAsset;
import com.cloudblue.connect.internal.connections.CBCConnection;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.internal.connections.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;

import com.cloudblue.connect.internal.connections.utils.URLBuilder;
import com.cloudblue.connect.internal.operations.parameters.NewRequestParameter;
import com.cloudblue.connect.internal.operations.parameters.common.ResourceActionParameter;
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

        URLBuilder urlBuilder = new URLBuilder()
                .addCollection("requests", getRequestParameter.getId());

        return connection.exchange(
                urlBuilder.build(),
                null,
                HttpMethod.GET,
                null,
                CBCRequest.class
        );
    }
    
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Asset")
    public CBCAsset getAsset(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Asset ID")  ResourceActionParameter getAssetParameter
    ) throws CBCException {
        URLBuilder urlBuilder = new URLBuilder()
                .addCollection("assets", getAssetParameter.getId());

        return connection.exchange(
                urlBuilder.build(),
                null,
                HttpMethod.GET,
                null,
                CBCAsset.class
        );
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Request")
    public CBCRequest createRequest(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Create Request Details") NewRequestParameter newRequestParameter
    ) throws CBCException {
        URLBuilder urlBuilder = new URLBuilder().addCollection("requests", null);

        return connection.exchange(
                urlBuilder.build(),
                newRequestParameter.buildEntity(),
                HttpMethod.GET,
                null,
                CBCRequest.class
        );
    }
}

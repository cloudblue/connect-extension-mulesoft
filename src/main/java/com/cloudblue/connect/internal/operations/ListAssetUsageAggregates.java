package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageAggregate;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListAssetUsageAggregates extends BaseListOperation {

    @Parameter
    @DisplayName("Asset ID")
    private String assetId;

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Asset Usage Aggregates")
    public List<CBCUsageAggregate> listAssetUsageAggregates(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCUsageAggregate>>() {})
                .collection(ASSETS, assetId)
                .collection(USAGE)
                .collection(AGGREGATES);

        resolve(q);

        return (List<CBCUsageAggregate>) q.get();
    }
}

package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageAggregate;
import com.cloudblue.connect.internal.operations.BaseListOperation;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.AGGREGATES;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.USAGE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListUsageAggregatesOperation extends BaseListOperation {
    @MediaType(value = ANY, strict = false)
    @DisplayName("List Usage Aggregates")
    public List<CBCUsageAggregate> listUsageAggregates(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCUsageAggregate>>() {})
                .collection(USAGE).collection(AGGREGATES);
        resolve(q);
        return (List<CBCUsageAggregate>) q.get();
    }
}

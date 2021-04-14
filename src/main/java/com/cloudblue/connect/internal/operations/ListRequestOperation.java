package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;
import com.cloudblue.connect.internal.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

public class ListRequestOperation {
    @Parameter
    Filter filter;

    @Parameter
    @Optional
    OrderBy orderBy;


    @MediaType(value = ANY, strict = false)
    @DisplayName("List Requests")
    public List<CBCRequest> listRequests(
            @Connection CBCConnection connection
    ) throws CBCException {

        String[] orderByValues = new String[]{};
        if (orderBy != null)
            orderByValues = orderBy.getProperties().toArray(new String[]{});

        return (List<CBCRequest>) connection
                .newQ(new TypeReference<ArrayList<CBCRequest>>() {})
                .collection("requests")
                .filter(filter.toRQL())
                .orderBy(orderByValues)
                .get();
    }
}

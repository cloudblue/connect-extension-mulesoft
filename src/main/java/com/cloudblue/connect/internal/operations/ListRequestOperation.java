package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCRequest;
import com.cloudblue.connect.api.parameters.common.Filter;
import com.cloudblue.connect.internal.connections.CBCConnection;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

public class ListRequestOperation {
    @Parameter
    Filter filter;

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Requests")
    public List<CBCRequest> listRequests(
            @Connection CBCConnection connection
    ) throws CBCException {
        return connection.newQ(new ArrayList<CBCRequest>())
                .collection("requests")
                .filter(filter.toRQL())
                .get();
    }
}

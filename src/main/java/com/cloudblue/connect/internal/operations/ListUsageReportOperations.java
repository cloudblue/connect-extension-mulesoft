package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.CBCUsageReport;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;

public class ListUsageReportOperations extends BaseListOperation {
    @MediaType(value = ANY, strict = false)
    @DisplayName("List Usage Reports")
    public List<CBCUsageReport> listUsageReports(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCUsageReport>>() {})
                .collection(USAGE).collection(FILES);
        resolve(q);
        return (List<CBCUsageReport>) q.get();
    }
}

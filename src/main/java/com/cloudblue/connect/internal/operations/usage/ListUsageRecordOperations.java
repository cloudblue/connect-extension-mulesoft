package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.internal.operations.BaseListOperation;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.RECORDS;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.USAGE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListUsageRecordOperations extends BaseListOperation {
    @MediaType(value = ANY, strict = false)
    @DisplayName("List Usage Records")
    public List<CBCUsageRecord> listUsageRecords(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCUsageRecord>>() {})
                .collection(USAGE).collection(RECORDS);
        resolve(q);
        return (List<CBCUsageRecord>) q.get();
    }
}

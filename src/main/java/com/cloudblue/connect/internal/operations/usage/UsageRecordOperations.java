package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.api.parameters.usage.record.BulkCloseUsageRecord;
import com.cloudblue.connect.api.parameters.usage.record.UpdateUsageRecord;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class UsageRecordOperations {
    @MediaType(value = ANY, strict = false)
    @DisplayName("Bulk Close Usage Record")
    public List<CBCUsageRecord> bulkCloseUsageRecord(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Bulk Close Usage Record Details") BulkCloseUsageRecord bulkCloseRecordDetails
    ) throws CBCException {
        return (List<CBCUsageRecord>) connection
                .newQ(new TypeReference<ArrayList<CBCUsageRecord>>() {})
                .collection(USAGE)
                .collection(RECORDS)
                .action(
                        "close-records",
                        HttpMethod.POST,
                        bulkCloseRecordDetails.buildEntity()
                );
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Update Usage Record")
    public CBCUsageRecord updateUsageRecord(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Update Usage Record Details") UpdateUsageRecord updateUsageRecordDetails
    ) throws CBCException {
        return (CBCUsageRecord) connection
                .newQ(new TypeReference<CBCUsageRecord>() {})
                .collection(USAGE)
                .collection(RECORDS, updateUsageRecordDetails.getId())
                .update(updateUsageRecordDetails.buildEntity());
    }
}

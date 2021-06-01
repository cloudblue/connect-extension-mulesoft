package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageRecord;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.api.parameters.usage.record.CloseUsageRecord;
import com.cloudblue.connect.api.parameters.usage.record.UpdateUsageRecord;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class UsageRecordOperations {
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Usage Record")
    public CBCUsageRecord getUsageRecord(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Usage Record ID") ResourceActionParameter resourceActionParameter
    ) throws CBCException {
        return (CBCUsageRecord) connection
                .newQ(new TypeReference<CBCUsageRecord>() {})
                .collection(USAGE)
                .collection(RECORDS, resourceActionParameter.getId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Close Usage Record")
    public  CBCUsageRecord closeUsageRecord(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Close Usage Record Details") CloseUsageRecord closeUsageRecordDetails
    ) throws CBCException {
        return (CBCUsageRecord) connection
                .newQ(new TypeReference<CBCUsageRecord>() {})
                .collection(USAGE)
                .collection(RECORDS, closeUsageRecordDetails.getId())
                .action("close", HttpMethod.POST, closeUsageRecordDetails.buildEntity());
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

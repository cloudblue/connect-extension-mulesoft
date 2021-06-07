package com.cloudblue.connect.internal.operations.usage;


import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageReport;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.api.parameters.usage.report.NewUsageReportParameter;
import com.cloudblue.connect.api.parameters.usage.report.UpdateUsageReportParameter;
import com.cloudblue.connect.api.parameters.usage.report.UsageReportAction;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.*;

public class UsageReportOperations {
    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Usage Report")
    public CBCUsageReport getUsageReport(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Usage Report ID") ResourceActionParameter resourceActionParameter
    ) throws CBCException {
        return (CBCUsageReport) connection
                .newQ(new TypeReference<CBCUsageReport>() {})
                .collection(USAGE)
                .collection(FILES, resourceActionParameter.getId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Create Usage Report")
    public CBCUsageReport createUsageReport(
            @Connection CBCConnection connection,
            @ParameterGroup(name="New Usage Report Details") NewUsageReportParameter usageReportParameter
    ) throws CBCException {
        return (CBCUsageReport) connection
                .newQ(new TypeReference<CBCUsageReport>() {})
                .collection(USAGE)
                .collection(FILES)
                .create(usageReportParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Update Usage Report")
    public CBCUsageReport updateUsageReport(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Update Usage Report Details") UpdateUsageReportParameter usageReportParameter
    ) throws CBCException {
        return (CBCUsageReport) connection
                .newQ(new TypeReference<CBCUsageReport>() {})
                .collection(USAGE)
                .collection(FILES, usageReportParameter.getId())
                .update(usageReportParameter.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Usage Report Action")
    public CBCUsageReport usageReportAction(
            @Connection CBCConnection connection,
            UsageReportAction usageReportActionDetails
    ) throws CBCException {
        return (CBCUsageReport) connection
                .newQ(new TypeReference<CBCUsageReport>() {})
                .collection(USAGE)
                .collection(FILES, usageReportActionDetails.getId())
                .action(usageReportActionDetails.action(), HttpMethod.POST, usageReportActionDetails.buildEntity());
    }
}

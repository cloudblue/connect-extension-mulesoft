/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.usage;


import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.usage.report.NewUsageReportParameter;
import com.cloudblue.connect.api.parameters.usage.report.UpdateUsageReportParameter;
import com.cloudblue.connect.api.parameters.usage.report.UsageReportAction;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.HttpConstants;

import java.io.InputStream;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.*;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class UsageReportOperations {
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Usage Report")
    @OutputJsonType(schema = "UsageReport-schema.json")
    public Result<InputStream, CBCResponseAttributes> createUsageReport(
            @Connection CBCConnection connection,
            @ParameterGroup(name="New Usage Report Details") NewUsageReportParameter usageReportParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(USAGE)
                .collection(FILES)
                .create(usageReportParameter.buildEntity());
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Update Usage Report")
    @OutputJsonType(schema = "UsageReport-schema.json")
    public Result<InputStream, CBCResponseAttributes> updateUsageReport(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Update Usage Report Details") UpdateUsageReportParameter usageReportParameter
    ) throws MuleException {
        return connection.newQ()
                .collection(USAGE)
                .collection(FILES, usageReportParameter.getId())
                .update(usageReportParameter.buildEntity());
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Usage Report Action")
    @OutputJsonType(schema = "UsageReport-schema.json")
    public Result<InputStream, CBCResponseAttributes> usageReportAction(
            @Connection CBCConnection connection,
            UsageReportAction usageReportActionDetails
    ) throws MuleException {
        return connection.newQ()
                .collection(USAGE)
                .collection(FILES, usageReportActionDetails.getId())
                .action(usageReportActionDetails.action(), HttpConstants.Method.POST, usageReportActionDetails.buildEntity());
    }
}

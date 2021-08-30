/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.usage;

import com.cloudblue.connect.api.models.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.usage.recon.CreateUsageReconParameters;
import com.cloudblue.connect.api.parameters.usage.recon.UsageReconFileDownload;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.fixed.OutputJsonType;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;

import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.RECONCILIATIONS;
import static com.cloudblue.connect.api.clients.constants.APIConstants.CollectionKeys.USAGE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class UsageReconciliationOperations {

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Upload/Create Usage Reconciliation File")
    @OutputJsonType(schema = "UsageReconciliation-schema.json")
    public Result<InputStream, CBCResponseAttributes> uploadUsageReconciliationFile(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Upload Reconciliations File Details") CreateUsageReconParameters createUsageReconParameters
    ) throws MuleException {

        return connection.newQ()
                .collection(USAGE)
                .collection(RECONCILIATIONS)
                .create(createUsageReconParameters.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Usage Reconciliation File Download")
    public Result<Void, CBCResponseAttributes> usageReconciliationAction(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Reconciliations File Download Details") UsageReconFileDownload usageReconFileDownload
    ) throws MuleException {

        return connection.newQ()
                .collection(USAGE)
                .collection(RECONCILIATIONS, usageReconFileDownload.getId())
                .collection(usageReconFileDownload.action())
                .download(
                        usageReconFileDownload.getLocation(),
                        usageReconFileDownload.getFileName()
                );
    }
}

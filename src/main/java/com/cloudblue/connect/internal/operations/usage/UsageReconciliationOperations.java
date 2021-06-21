package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageReconciliation;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.api.parameters.usage.recon.CreateUsageReconParameters;
import com.cloudblue.connect.api.parameters.usage.recon.UsageReconFileDownload;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.extension.api.annotation.Ignore;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.RECONCILIATIONS;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.USAGE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class UsageReconciliationOperations {
    @Ignore
    public CBCUsageReconciliation getUsageReconciliation(
            CBCConnection connection,
            ResourceActionParameter resourceActionParameter
    ) throws CBCException {

        return (CBCUsageReconciliation) connection
                .newQ(new TypeReference<CBCUsageReconciliation>() {})
                .collection(USAGE)
                .collection(RECONCILIATIONS, resourceActionParameter.getId())
                .get();
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Upload/Create Usage Reconciliation File")
    public CBCUsageReconciliation uploadUsageReconciliationFile(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Upload Reconciliations File Details") CreateUsageReconParameters createUsageReconParameters
    ) throws CBCException {

        return (CBCUsageReconciliation) connection
                .newQ(new TypeReference<CBCUsageReconciliation>() {})
                .collection(USAGE)
                .collection(RECONCILIATIONS)
                .create(createUsageReconParameters.buildEntity());
    }

    @MediaType(value = ANY, strict = false)
    @DisplayName("Usage Reconciliation File Download")
    public CBCUsageReconciliation usageReconciliationAction(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Reconciliations File Download Details") UsageReconFileDownload usageReconFileDownload
    ) throws CBCException {

        connection
                .newQ(null)
                .collection(USAGE)
                .collection(RECONCILIATIONS, usageReconFileDownload.getId())
                .collection(usageReconFileDownload.action())
                .download(
                        usageReconFileDownload.getLocation(),
                        usageReconFileDownload.getFileName()
                );

        return getUsageReconciliation(connection, usageReconFileDownload);
    }
}

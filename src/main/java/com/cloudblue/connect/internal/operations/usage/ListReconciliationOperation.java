package com.cloudblue.connect.internal.operations.usage;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.usage.CBCUsageChunkFile;
import com.cloudblue.connect.api.models.usage.CBCUsageReconciliation;
import com.cloudblue.connect.internal.operations.BaseListOperation;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.RECONCILIATIONS;
import static com.cloudblue.connect.api.clients.constants.CBCAPIConstants.CollectionKeys.USAGE;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListReconciliationOperation extends BaseListOperation {
    @MediaType(value = ANY, strict = false)
    @DisplayName("List Usage Reconciliations")
    public List<CBCUsageReconciliation> listUsageReconciliations(
            @Connection CBCConnection connection
    ) throws CBCException {

        Client.Q q = connection
                .newQ(new TypeReference<ArrayList<CBCUsageReconciliation>>() {})
                .collection(USAGE)
                .collection(RECONCILIATIONS);

        resolve(q);

        return (List<CBCUsageReconciliation>) q.get();
    }
}

package com.cloudblue.connect.internal.sources.common;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.internal.operations.BaseListOperation;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import com.fasterxml.jackson.core.type.TypeReference;

public class ListObject extends BaseListOperation {

    public Object list(
            CBCConnection connection,
            TypeReference typeRef,
            String collection
    ) throws CBCException {

        Client.Q q = connection
                .newQ(typeRef)
                .collection(collection);

        resolve(q);

        return q.get();
    }
}

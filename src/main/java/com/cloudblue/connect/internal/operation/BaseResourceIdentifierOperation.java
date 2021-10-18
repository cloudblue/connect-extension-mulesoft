/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

import java.util.Map;

public class BaseResourceIdentifierOperation {

    protected CBCConnection.Q getQ(CBCConnection connection,
                                   String resourceType,
                                   String action,
                                   Map<String, Object> resourceIdentifier) {

        Metadata metadata = MetadataUtil.getMetadata(resourceType);
        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(resourceType, action);

        CBCConnection.Q q = connection.newQ();

        if (metadata.isSubCollection()) {
            String parentId = (String) resourceIdentifier.get(metadata.getParentId().getField());

            q.collection(metadata.getParentCollection(), parentId);
        }

        if (!actionMetadata.getFilters().isEmpty()) {
            for (Keys filter : actionMetadata.getFilters()) {
                q.filter(filter.getField(),
                        (String) resourceIdentifier.get(filter.getField()));
            }
        }

        if (actionMetadata.isCollectionAction()) {
            return q.collection(metadata.getCollection());
        } else {
            String id = (String) resourceIdentifier.get(metadata.getId().getField());
            return q.collection(metadata.getCollection(), id);
        }
    }
}

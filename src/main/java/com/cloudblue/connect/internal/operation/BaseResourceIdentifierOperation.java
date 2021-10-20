/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfoUtil;
import com.cloudblue.connect.internal.metadata.Keys;

import java.util.Map;

public class BaseResourceIdentifierOperation {

    protected CBCConnection.Query getQuery(CBCConnection connection,
                                           String resourceType,
                                           String action,
                                           Map<String, Object> resourceIdentifier) {

        CollectionInfo collectionInfo = CollectionInfoUtil.getCollectionInfo(resourceType);
        ActionInfo actionInfo = CollectionInfoUtil.getActionInfo(resourceType, action);

        CBCConnection.Query query = connection.newQuery();

        if (collectionInfo.isSubCollection()) {
            String parentId = (String) resourceIdentifier.get(collectionInfo.getParentId().getField());

            query.collection(collectionInfo.getParentCollection(), parentId);
        }

        if (!actionInfo.getFilters().isEmpty()) {
            for (Keys filter : actionInfo.getFilters()) {
                query.filter(filter.getField(),
                        (String) resourceIdentifier.get(filter.getField()));
            }
        }

        if (actionInfo.isCollectionAction()) {
            return query.collection(collectionInfo.getCollection());
        } else {
            String id = (String) resourceIdentifier.get(collectionInfo.getId().getField());
            return query.collection(collectionInfo.getCollection(), id);
        }
    }
}

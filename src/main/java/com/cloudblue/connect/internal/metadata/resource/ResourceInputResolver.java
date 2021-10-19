/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.resource;

import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfoUtil;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;

public class ResourceInputResolver {

    public MetadataType getInputMetadata(MetadataContext context, String resourceType, String action)
            throws MetadataResolvingException {
        CollectionInfo collectionInfo = CollectionInfoUtil.getCollectionInfo(resourceType);
        ActionInfo actionInfo = CollectionInfoUtil.getActionInfo(resourceType, action);

        if (actionInfo == null) {
            throw new MetadataResolvingException("No Metadata is available.",
                    FailureCode.NO_DYNAMIC_TYPE_AVAILABLE);
        } else if (actionInfo.getInput() != null) {
            return actionInfo.getInput().getMetadataType(context, collectionInfo,
                    Action.valueOf(action.toUpperCase()), actionInfo);
        } else {
            return context.getTypeBuilder().nothingType().build();
        }
    }

}

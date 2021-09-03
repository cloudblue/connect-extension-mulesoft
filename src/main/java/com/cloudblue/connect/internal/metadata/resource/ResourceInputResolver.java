/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.resource;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;

public class ResourceInputResolver {

    public MetadataType getInputMetadata(MetadataContext context, String resourceType, String action)
            throws MetadataResolvingException {
        Metadata metadata = MetadataUtil.getMetadata(resourceType);
        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(resourceType, action);

        if (actionMetadata == null) {
            throw new MetadataResolvingException("No Metadata is available.",
                    FailureCode.NO_DYNAMIC_TYPE_AVAILABLE);
        } else if (actionMetadata.getInput() != null) {
            return actionMetadata.getInput().getMetadataType(context, metadata, actionMetadata);
        } else {
            return context.getTypeBuilder().voidType().build();
        }
    }

}

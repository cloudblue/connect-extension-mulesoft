/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.fulfillment;

import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.MetadataProvider;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class AssignRequestMetadataProvider
        extends BaseRequestMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        CollectionInfo collectionInfo,
                                        Action action,
                                        ActionInfo actionInfo) {
        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        includeId(objectBuilder, collectionInfo);

        objectBuilder.addField()
                .key(Keys.EMAIL.getField())
                .label(Keys.EMAIL.getLabel())
                .required()
                .value()
                .stringType();

        objectBuilder.addField()
                .key(Keys.FORCE.getField())
                .label(Keys.FORCE.getLabel())
                .value()
                .booleanType();

        return objectBuilder.build();
    }
}

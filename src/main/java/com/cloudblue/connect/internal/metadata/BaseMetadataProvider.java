/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class BaseMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context, Metadata metadata) {
        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        if (metadata.isSubCollection()) {
            objectBuilder.addField()
                    .key(metadata.getParentId().getField())
                    .label(metadata.getParentId().getLabel())
                    .required()
                    .value()
                    .stringType();
        }

        objectBuilder.addField()
                .key(metadata.getId().getField())
                .label(metadata.getId().getLabel())
                .required()
                .value()
                .stringType();

        return objectBuilder.build();
    }
}

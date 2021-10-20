/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class BaseMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        CollectionInfo collectionInfo,
                                        Action action,
                                        ActionInfo actionInfo) {
        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        if (collectionInfo.isSubCollection()) {
            objectBuilder.addField()
                    .key(collectionInfo.getParentId().getField())
                    .label(collectionInfo.getParentId().getLabel())
                    .required()
                    .value()
                    .stringType();
        }

        objectBuilder.addField()
                .key(collectionInfo.getId().getField())
                .label(collectionInfo.getId().getLabel())
                .required()
                .value()
                .stringType();

        if (!actionInfo.getFilters().isEmpty()) {
            for (Keys filter : actionInfo.getFilters()) {
                objectBuilder.addField()
                        .key(filter.getField())
                        .label(filter.getLabel())
                        .required()
                        .value()
                        .stringType();
            }
        }

        if (!actionInfo.getFormAttributes().isEmpty()) {
            for (Keys attributes : actionInfo.getFormAttributes()) {
                objectBuilder.addField()
                        .key(attributes.getField())
                        .label(attributes.getLabel())
                        .required()
                        .value()
                        .stringType();
            }
        }

        return objectBuilder.build();
    }
}

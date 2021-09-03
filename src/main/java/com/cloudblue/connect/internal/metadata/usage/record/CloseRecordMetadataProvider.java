/*
 * Copyright � 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.usage.record;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataProvider;

import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class CloseRecordMetadataProvider
        extends BaseRecordMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        Metadata metadata,
                                        ActionMetadata actionMetadata) {

        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        includeId(objectBuilder, metadata);
        includeExternalBillingId(objectBuilder);
        includeExternalBillingNote(objectBuilder);

        return objectBuilder.build();
    }
}
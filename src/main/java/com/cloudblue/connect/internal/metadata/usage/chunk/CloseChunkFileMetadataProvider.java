/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.usage.chunk;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataProvider;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class CloseChunkFileMetadataProvider
        extends BaseChunkFileMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        Metadata metadata,
                                        ActionMetadata actionMetadata) {

        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        includeId(objectBuilder, metadata);

        objectBuilder.addField()
                .key(Keys.EXTERNAL_BILLING_ID.getField())
                .label(Keys.EXTERNAL_BILLING_ID.getLabel())
                .required()
                .value()
                .stringType();

        objectBuilder.addField()
                .key(Keys.EXTERNAL_BILLING_NOTE.getField())
                .label(Keys.EXTERNAL_BILLING_NOTE.getLabel())
                .value()
                .stringType();

        return objectBuilder.build();
    }
}

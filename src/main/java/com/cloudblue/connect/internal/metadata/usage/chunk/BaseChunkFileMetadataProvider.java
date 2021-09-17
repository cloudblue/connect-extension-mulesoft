/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.usage.chunk;

import com.cloudblue.connect.internal.metadata.Metadata;
import org.mule.metadata.api.builder.ObjectTypeBuilder;

public class BaseChunkFileMetadataProvider {
    protected void includeId(final ObjectTypeBuilder objectBuilder, Metadata metadata) {
        objectBuilder.addField()
                .key(metadata.getId().getField())
                .required()
                .label("Chunk File ID")
                .value()
                .stringType();
    }
}

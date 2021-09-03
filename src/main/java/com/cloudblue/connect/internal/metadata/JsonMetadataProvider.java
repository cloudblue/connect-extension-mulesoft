/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;

public class JsonMetadataProvider
        extends JsonMetadataBuilder implements MetadataProvider {
    private String schemaFileName;

    public JsonMetadataProvider(String schemaFileName) {
        this.schemaFileName = schemaFileName;
    }

    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        Metadata metadata,
                                        ActionMetadata actionMetadata
    ) throws MetadataResolvingException {

        return getType(schemaFileName);
    }
}

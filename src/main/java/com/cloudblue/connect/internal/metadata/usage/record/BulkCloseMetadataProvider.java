/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.usage.record;

import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.MetadataProvider;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.builder.ArrayTypeBuilder;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class BulkCloseMetadataProvider
        extends BaseRecordMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        CollectionInfo collectionInfo,
                                        Action action,
                                        ActionInfo actionInfo) {
        final ArrayTypeBuilder arrayBuilder = context.getTypeBuilder().arrayType();
        final ObjectTypeBuilder objectBuilder = arrayBuilder.of().objectType();

        objectBuilder.addField()
                .key(Keys.ID.getField())
                .required()
                .label("Usage Record ID")
                .value()
                .stringType();

        includeExternalBillingId(objectBuilder);
        includeExternalBillingNote(objectBuilder);

        return arrayBuilder.build();
    }
}

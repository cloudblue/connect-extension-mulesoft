/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.usage.report;

import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.MetadataProvider;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;

public class AcceptReportMetadataProvider
        extends BaseReportMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context,
                                        CollectionInfo collectionInfo,
                                        Action action,
                                        ActionInfo actionInfo)
            throws MetadataResolvingException {
        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        includeId(objectBuilder, collectionInfo);

        objectBuilder.addField()
                .key(Keys.ACCEPTANCE_NOTE.getField())
                .label(Keys.ACCEPTANCE_NOTE.getField())
                .required()
                .value()
                .stringType();

        return objectBuilder.build();
    }
}

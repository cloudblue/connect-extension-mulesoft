/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.tier;

import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataProvider;

import org.mule.metadata.api.builder.ObjectTypeBuilder;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;

public class ApproveTcrMetadataProvider implements MetadataProvider {
    @Override
    public MetadataType getMetadataType(MetadataContext context, Metadata metadata) {
        final ObjectTypeBuilder objectBuilder = context.getTypeBuilder().objectType();

        objectBuilder.addField()
                .key(metadata.getId().getField())
                .required()
                .label("Tier Config Request ID")
                .value()
                .stringType();
        objectBuilder.addField()
                .key(Keys.EFFECTIVE_DATE.getField())
                .label(Keys.EFFECTIVE_DATE.getLabel())
                .value()
                .stringType();

        ObjectTypeBuilder templateObject = objectBuilder.addField()
                .key(Keys.TEMPLATE.getField())
                .label(Keys.TEMPLATE.getLabel())
                .required()
                .value()
                .objectType();
        templateObject.addField()
                .key(Keys.ID.getField())
                .label(Keys.TEMPLATE_ID.getLabel())
                .required()
                .value()
                .stringType();

        return objectBuilder.build();
    }
}

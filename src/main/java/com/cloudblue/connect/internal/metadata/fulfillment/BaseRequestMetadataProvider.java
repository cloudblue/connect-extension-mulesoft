/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.fulfillment;

import com.cloudblue.connect.internal.metadata.CollectionInfo;
import com.cloudblue.connect.internal.metadata.Keys;
import org.mule.metadata.api.builder.ObjectTypeBuilder;

public class BaseRequestMetadataProvider {
    protected void includeId(final ObjectTypeBuilder objectBuilder, CollectionInfo collectionInfo) {
        objectBuilder.addField()
                .key(collectionInfo.getId().getField())
                .required()
                .label("Fulfillment Request ID")
                .value()
                .stringType();
    }

    protected void includeTemplateId(final ObjectTypeBuilder objectBuilder) {
        objectBuilder.addField()
                .key(Keys.TEMPLATE_ID.getField())
                .label(Keys.TEMPLATE_ID.getLabel())
                .required()
                .value()
                .stringType();
    }
}

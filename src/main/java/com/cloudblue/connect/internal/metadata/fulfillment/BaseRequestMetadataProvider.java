/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.fulfillment;

import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.Metadata;
import org.mule.metadata.api.builder.ObjectTypeBuilder;

public class BaseRequestMetadataProvider {
    protected void includeId(final ObjectTypeBuilder objectBuilder, Metadata metadata) {
        objectBuilder.addField()
                .key(metadata.getId().getField())
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

    protected ObjectTypeBuilder includeAsset(final ObjectTypeBuilder objectBuilder) {
        return objectBuilder.addField()
                .key(Keys.ASSET.getField())
                .label(Keys.ASSET.getLabel())
                .required()
                .value()
                .objectType();
    }

    protected void includeParams(final ObjectTypeBuilder objectBuilder) {
        ObjectTypeBuilder paramTypeBuilder = objectBuilder.addField()
                .key(Keys.PARAMS.getField())
                .label(Keys.PARAMS.getLabel())
                .required()
                .value()
                .arrayType().of().objectType();

        paramTypeBuilder.addField()
                .key(Keys.ID.getField())
                .label("Parameter ID")
                .required()
                .value()
                .stringType();

        paramTypeBuilder.addField()
                .key(Keys.VALUE.getField())
                .label(Keys.VALUE.getLabel())
                .value()
                .stringType();

        paramTypeBuilder.addField()
                .key(Keys.STRUCTURED_VALUE.getField())
                .label(Keys.STRUCTURED_VALUE.getLabel())
                .value()
                .objectType();

        paramTypeBuilder.addField()
                .key(Keys.VALUE_ERROR.getField())
                .label(Keys.VALUE_ERROR.getLabel())
                .value()
                .stringType();
    }
}

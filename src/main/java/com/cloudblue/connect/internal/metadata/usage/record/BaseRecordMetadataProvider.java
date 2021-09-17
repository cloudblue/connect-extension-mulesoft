/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.usage.record;

import com.cloudblue.connect.internal.metadata.Keys;
import com.cloudblue.connect.internal.metadata.Metadata;
import org.mule.metadata.api.builder.ObjectTypeBuilder;

public class BaseRecordMetadataProvider {
    protected void includeId(final ObjectTypeBuilder objectBuilder, final Metadata metadata) {
        objectBuilder.addField()
                .key(metadata.getId().getField())
                .required()
                .label("Usage Record ID")
                .value()
                .stringType();
    }

    protected void includeExternalBillingId(final ObjectTypeBuilder objectBuilder) {
        objectBuilder.addField()
                .key(Keys.EXTERNAL_BILLING_ID.getField())
                .label(Keys.EXTERNAL_BILLING_ID.getLabel())
                .required()
                .value()
                .stringType();
    }

    protected void includeExternalBillingNote(final ObjectTypeBuilder objectBuilder) {
        objectBuilder.addField()
                .key(Keys.EXTERNAL_BILLING_NOTE.getField())
                .label(Keys.EXTERNAL_BILLING_NOTE.getLabel())
                .value()
                .stringType();
    }
}

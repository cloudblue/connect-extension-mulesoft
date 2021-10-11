/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.usage.report;

import com.cloudblue.connect.internal.metadata.Metadata;
import org.mule.metadata.api.builder.ObjectTypeBuilder;

public class BaseReportMetadataProvider {
    protected void includeId(final ObjectTypeBuilder objectBuilder, final Metadata metadata) {
        objectBuilder.addField()
                .key(metadata.getId().getField())
                .required()
                .label("Usage Report ID")
                .value()
                .stringType();
    }
}

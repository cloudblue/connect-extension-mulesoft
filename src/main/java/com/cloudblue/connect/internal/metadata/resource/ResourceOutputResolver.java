/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.resource;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

import org.apache.commons.io.IOUtils;

import org.mule.metadata.api.model.MetadataType;
import org.mule.metadata.json.api.JsonTypeLoader;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceOutputResolver {

    public MetadataType getOutputType(MetadataContext context, String resourceType, String action)
            throws MetadataResolvingException {
        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(resourceType, action);
        MetadataResolvingException resolvingException = new MetadataResolvingException("No Metadata is available.",
                FailureCode.NO_DYNAMIC_TYPE_AVAILABLE);
        try {
            if (actionMetadata == null) {
                throw resolvingException;
            } else {
                if (actionMetadata.getOutput() != null
                        && !actionMetadata.getOutput().equals(MetadataUtil.NO_OUTPUT_SCHEMA)) {
                    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                    InputStream stream = classLoader.getResourceAsStream(actionMetadata.getOutput());
                    String schema = IOUtils.toString(stream, StandardCharsets.UTF_8);

                    return new JsonTypeLoader(schema).load(null).orElseThrow(
                            () -> resolvingException
                    );
                } else {
                    return context.getTypeBuilder().voidType().build();
                }

            }
        } catch (IOException e) {
            throw resolvingException;
        }
    }

    public String getCategoryName() {
        return "Resources";
    }
}

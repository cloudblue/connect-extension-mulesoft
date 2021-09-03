/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.apache.commons.io.IOUtils;

import org.mule.metadata.api.model.MetadataType;
import org.mule.metadata.json.api.JsonTypeLoader;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonMetadataBuilder {
    public MetadataType getType(String schemaFileName)
            throws MetadataResolvingException {
        MetadataResolvingException resolvingException = new MetadataResolvingException("No Metadata is available.",
                FailureCode.NO_DYNAMIC_TYPE_AVAILABLE);
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream(schemaFileName);
            String schema = IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8);

            return new JsonTypeLoader(schema).load(null).orElseThrow(
                    () -> resolvingException
            );
        } catch (IOException e) {
            throw resolvingException;
        }
    }
}

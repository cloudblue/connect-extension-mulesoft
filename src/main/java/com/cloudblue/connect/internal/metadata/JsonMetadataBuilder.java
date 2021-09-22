/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.internal.model.resource.Action;
import org.apache.commons.io.IOUtils;

import org.mule.metadata.api.model.MetadataType;
import org.mule.metadata.json.api.JsonTypeLoader;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.FailureCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonMetadataBuilder {

    private static final Logger logger = LoggerFactory.getLogger(JsonMetadataBuilder.class);

    private static final String LIST_SCHEMA_TEMPLATE = "{\"$schema\" : \"http://json-schema.org/draft-07/schema#\", \"type\": \"array\", \"items\": %s}";

    private String makeListSchema(String objectSchema) {
        return String.format(LIST_SCHEMA_TEMPLATE, objectSchema);
    }

    public MetadataType getType(String schemaFileName, Action action)
            throws MetadataResolvingException {
        MetadataResolvingException resolvingException = new MetadataResolvingException("No Metadata is available.",
                FailureCode.NO_DYNAMIC_TYPE_AVAILABLE);
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream(schemaFileName);
            String schema = IOUtils.toString(Objects.requireNonNull(stream), StandardCharsets.UTF_8);

            if (action == Action.LIST) {
                schema = makeListSchema(schema);
            }

            return new JsonTypeLoader(schema).load(null).orElseThrow(
                    () -> resolvingException
            );
        } catch (IOException e) {
            logger.error("Error during loading JSON metadata from file " + schemaFileName, e);
            throw resolvingException;
        }
    }

}

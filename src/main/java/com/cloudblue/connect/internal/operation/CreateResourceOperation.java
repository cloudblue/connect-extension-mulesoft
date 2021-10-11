/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.error.provider.OperationErrorTypeProvider;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import com.cloudblue.connect.internal.metadata.resource.create.CreateCollectionIdentifierInputResolver;
import com.cloudblue.connect.internal.metadata.resource.create.CreateResourceInputResolver;
import com.cloudblue.connect.internal.metadata.resource.create.CreateResourceOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.create.CreateResourceTypeKeysResolver;

import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

/***
 * Create resource operation to create different type of resources on CloudBlue Connect
 */
@Throws(OperationErrorTypeProvider.class)
public class CreateResourceOperation {
    /**
     * Resource identifier.
     */
    @Parameter
    @ParameterGroup(name = "Create Resource Type")
    @Placement(order = 1)
    @MetadataKeyId(CreateResourceTypeKeysResolver.class)
    ActionIdentifier identifier;

    /***
     * The operation to create a resource on CloudBlue Connect
     *
     * @param connection the connection required to execute the operation.
     * @param collectionIdentifier the input needed to map parent collection in case of sub collection.
     * @param createResourceParameter the constructed request body to perform the resource creation.
     * @return JSON representation of created resource as result.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Create Resource")
    @OutputResolver(output = CreateResourceOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> createResource(
            @Connection CBCConnection connection,
            @TypeResolver(CreateCollectionIdentifierInputResolver.class)
            Map<String, Object> collectionIdentifier,
            @TypeResolver(CreateResourceInputResolver.class)
            @Content InputStream createResourceParameter
    ) {
        Metadata metadata = MetadataUtil.getMetadata(identifier.getResourceType());

        CBCConnection.Q q = connection.newQ();

        if (metadata.isSubCollection()) {
            String parentId = (String) collectionIdentifier.get(metadata.getParentId().getField());

            q.collection(metadata.getParentCollection(), parentId);
        }

        return q.collection(metadata.getCollection())
                .create(createResourceParameter);
    }
}

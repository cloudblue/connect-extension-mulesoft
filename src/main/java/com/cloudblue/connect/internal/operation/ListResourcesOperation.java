/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.error.provider.OperationErrorTypeProvider;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import com.cloudblue.connect.internal.metadata.resource.list.ListResourceInputResolver;
import com.cloudblue.connect.internal.metadata.resource.list.ListResourceOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.list.ListResourceTypeKeysResolver;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.NullSafe;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

@Throws(OperationErrorTypeProvider.class)
public class ListResourcesOperation extends BaseListOperation {

    /**
     * Resource identifier.
     */
    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId(ListResourceTypeKeysResolver.class)
    @Placement(order = 1)
    private String resourceType;

    /**
     *
     * The operation to get list of a specific type of resource on CloudBlue Connect identified by parent Id if any.
     *
     * @param connection the connection required to execute the operation.
     * @param listResourceParameter the resource parent Ids (if any) needed to fetch the resource list.
     * @return Json representation of resource list as a payload and {@link CBCResponseAttributes} for operation http headers.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("List Resources")
    @OutputResolver(output = ListResourceOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> listResources(
            @Connection CBCConnection connection,
            @TypeResolver(ListResourceInputResolver.class)
            @Content @Optional @NullSafe Map<String, Object> listResourceParameter
    ) {
        Metadata metadata = MetadataUtil.getMetadata(resourceType);

        CBCConnection.Q q = connection.newQ();

        if (metadata.isSubCollection()) {
            String parentId = (String) listResourceParameter.get(metadata.getParentId().getField());

            q.collection(metadata.getParentCollection(), parentId);
        }

        q.collection(metadata.getCollection());

        resolve(q);

        return q.get();
    }
}

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
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceInputResolver;
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceTypeKeysResolver;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

/***
 * Get resource operation to fetch details of different type of resources on CloudBlue Connect using Id of the resource.
 */
@Throws(OperationErrorTypeProvider.class)
public class GetResourceOperation extends BaseResourceIdentifierOperation {
    /**
     * Resource identifier.
     */
    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId(GetResourceTypeKeysResolver.class)
    @Placement(order = 1)
    private String resourceType;

    /**
     *
     * The operation to get details of a resource on CloudBlue Connect identified by resource Id and parent Id if any.
     *
     * @param connection the connection required to execute the operation.
     * @param getResourceParameter the resource Ids needed to fetch the resource details.
     * @return Json representation of resource details as a payload and {@link CBCResponseAttributes} for operation http headers.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Get Resource")
    @OutputResolver(output = GetResourceOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> getResource(
            @Connection CBCConnection connection,
            @TypeResolver(GetResourceInputResolver.class)
            @Content Map<String, Object> getResourceParameter
    ) {

        return getQuery(connection, resourceType, Action.GET.name(), getResourceParameter)
                .get();
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.error.provider.OperationErrorTypeProvider;
import com.cloudblue.connect.internal.metadata.resource.update.UpdateResourceIdentifierInputResolver;
import com.cloudblue.connect.internal.metadata.resource.update.UpdateResourceInputResolver;
import com.cloudblue.connect.internal.metadata.resource.update.UpdateResourceOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.update.UpdateResourceTypeKeysResolver;
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

@Throws(OperationErrorTypeProvider.class)
public class UpdateResourceOperation extends BaseResourceIdentifierOperation {

    /**
     * Resource identifier.
     */
    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId(UpdateResourceTypeKeysResolver.class)
    @Placement(order = 1)
    private String resourceType;

    /**
     *
     * The operation to perform update on a specific type of resource on CloudBlue Connect
     * identified by resource Id and parent Id if any.
     *
     * @param connection the connection required to execute the operation.
     * @param updateResourceIdentifier the resource Ids needed to perform the update operation on the resource.
     * @param updatePayload the constructed request body to perform the resource update.
     * @return Json representation of resource update result as a payload and {@link CBCResponseAttributes} for operation http headers.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Update Resource")
    @OutputResolver(output = UpdateResourceOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> updateResource(
            @Connection CBCConnection connection,
            @TypeResolver(UpdateResourceIdentifierInputResolver.class)
            Map<String, Object> updateResourceIdentifier,
            @TypeResolver(UpdateResourceInputResolver.class)
            @Content InputStream updatePayload
    ) {
        return getQ(connection, resourceType, Action.UPDATE.name(), updateResourceIdentifier)
                .update(updatePayload);
    }
}

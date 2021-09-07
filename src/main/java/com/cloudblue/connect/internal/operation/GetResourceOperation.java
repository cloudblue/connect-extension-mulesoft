/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceInputResolver;
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.get.GetResourceTypeKeysResolver;
import com.cloudblue.connect.internal.model.resource.Action;

import org.mule.runtime.api.exception.MuleException;
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

public class GetResourceOperation extends BaseResourceIdentifierOperation {
    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId(GetResourceTypeKeysResolver.class)
    @Placement(order = 1)
    private String resourceType;

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Get Resource")
    @OutputResolver(output = GetResourceOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> getResource(
            @Connection CBCConnection connection,
            @TypeResolver(GetResourceInputResolver.class)
            @Content Map<String, Object> getResourceParameter
    ) throws MuleException {

        return getQ(connection, resourceType, Action.GET.name(), getResourceParameter)
                .get();
    }
}

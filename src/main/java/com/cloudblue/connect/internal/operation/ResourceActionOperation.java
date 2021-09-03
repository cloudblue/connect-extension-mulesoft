/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;


import com.cloudblue.connect.api.parameters.filters.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.resource.action.*;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class ResourceActionOperation extends BaseResourceIdentifierOperation {
    Logger logger = LoggerFactory.getLogger("ResourceActionOperation");

    @Parameter
    @ParameterGroup(name = "Resource Action")
    @Placement(order = 1)
    @MetadataKeyId(ResourceActionTypeKeysResolver.class)
    ActionIdentifier identifier;

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Resource Action")
    @OutputResolver(output = ResourceActionOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> resourceAction(
            @Connection CBCConnection connection,
            @TypeResolver(ResourceActionInputResolver.class)
            @Content Object resourceActionParameter
    ) throws MuleException {
        Metadata metadata = MetadataUtil.getMetadata(identifier.getResourceType());

        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(
                identifier.getResourceType(), identifier.getAction());

        CBCConnection.Q q = connection.newQ();

        if (actionMetadata.isCollectionAction()) {
            q.collection(metadata.getCollection());
        } else if (resourceActionParameter instanceof Map) {
            q = getQ(connection,
                    identifier.getResourceType(),
                    identifier.getAction(),
                    (Map<String, Object>) resourceActionParameter);
        }

        String action = actionMetadata.getAction();

        if (action == null) {
            action = identifier.getAction().toLowerCase();
        }

        if (actionMetadata.isIncludePayload())
            return q.action(action, actionMetadata.getMethod(), resourceActionParameter);
        else
            return q.action(action, actionMetadata.getMethod(), null);
    }
}

/*
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
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.Metadata;
import com.cloudblue.connect.internal.metadata.resource.action.*;

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

@Throws(OperationErrorTypeProvider.class)
public class ResourceActionOperation extends BaseResourceIdentifierOperation {

    /**
     * Resource and Action identifier.
     */
    @Parameter
    @ParameterGroup(name = "Resource Action")
    @Placement(order = 1)
    @MetadataKeyId(ResourceActionTypeKeysResolver.class)
    ActionIdentifier identifier;

    /**
     *
     * The operation to perform an action on a specific type of resource on CloudBlue Connect
     * identified by resource Id and parent Id if any.
     *
     * @param connection the connection required to execute the operation.
     * @param resourceActionParameter the constructed request body and resource Id along with parent Id (if any)
     *                               to perform the resource action.
     * @return Json representation of resource action output as a payload and {@link CBCResponseAttributes} for operation http headers.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Resource Action")
    @OutputResolver(output = ResourceActionOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> resourceAction(
            @Connection CBCConnection connection,
            @TypeResolver(ResourceActionInputResolver.class)
            @Content Object resourceActionParameter
    ) {
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
            action = identifier.getAction();
        }

        if (actionMetadata.isIncludePayload())
            return q.action(action.toLowerCase(), actionMetadata.getMethod(), resourceActionParameter);
        else
            return q.action(action.toLowerCase(), actionMetadata.getMethod(), null);
    }
}

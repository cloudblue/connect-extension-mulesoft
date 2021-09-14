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
import com.cloudblue.connect.internal.metadata.resource.download.ResourceFileDownloadInputResolver;
import com.cloudblue.connect.internal.metadata.resource.download.ResourceFileDownloadTypeKeysResolver;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

@Throws(OperationErrorTypeProvider.class)
public class DownloadResourceFileOperation extends BaseFileOperation {

    @Parameter
    @DisplayName("Download Location")
    @Path(type = PathModel.Type.DIRECTORY, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 4)
    private String location;

    @Parameter
    @DisplayName("File Name")
    @Placement(order = 5)
    private String fileName;

    @Parameter
    @ParameterGroup(name = "Resource Action")
    @MetadataKeyId(ResourceFileDownloadTypeKeysResolver.class)
    ActionIdentifier actionIdentifier;

    @MediaType(value = ANY, strict = false)
    @DisplayName("Download File")
    public Result<Void, CBCResponseAttributes> downloadFile(
            @Connection CBCConnection connection,
            @TypeResolver(ResourceFileDownloadInputResolver.class)
            @Content Map<String, Object> downloadResourceFileIdentifier) throws MuleException {

        CBCConnection.Q q = getQ(connection,
                actionIdentifier.getResourceType(),
                actionIdentifier.getAction(),
                downloadResourceFileIdentifier);

        q.collection(getAction(actionIdentifier.getResourceType(), actionIdentifier.getAction()));

        return q.download(location, fileName);
    }
}

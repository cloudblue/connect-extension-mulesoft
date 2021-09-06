/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.internal.clients.entity.FileEntity;
import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadInputResolver;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadTypeKeysResolver;

import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.*;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

public class UploadResourceFileOperation extends BaseFileOperation {

    @Parameter
    @DisplayName("File Path")
    @Path(type = PathModel.Type.FILE, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 2)
    String file;

    @Parameter
    @ParameterGroup(name = "Resource Action")
    @Placement(order = 1)
    @MetadataKeyId(ResourceFileUploadTypeKeysResolver.class)
    ActionIdentifier actionIdentifier;


    private FileEntity buildPayload(ActionMetadata actionMetadata, Map<String, Object> params) {
        FileEntity fileEntity = new FileEntity()
                .addFile((String) params.get(actionMetadata.getFileName()), new File(file));

        actionMetadata.getFormAttributes()
                .forEach(attributes -> fileEntity
                        .addValue(attributes.getField(),
                                (String) params.get(attributes.getField())));

        return fileEntity;
    }

    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Upload File")
    @OutputResolver(output = ResourceFileUploadOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> uploadFile(
            @Connection CBCConnection connection,
            @TypeResolver(ResourceFileUploadInputResolver.class)
            @Content Map<String, Object> uploadResourceFileParameter) throws MuleException {

        CBCConnection.Q q = getQ(connection, actionIdentifier.getResourceType(),
                actionIdentifier.getAction(), uploadResourceFileParameter);

        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(actionIdentifier.getResourceType(),
                actionIdentifier.getAction());


        return q.action(getAction(actionIdentifier.getResourceType(), actionIdentifier.getAction()),
                actionMetadata.getMethod(),
                buildPayload(actionMetadata, uploadResourceFileParameter));
    }
}

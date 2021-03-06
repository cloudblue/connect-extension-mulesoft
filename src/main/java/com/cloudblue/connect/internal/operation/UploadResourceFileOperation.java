/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.clients.entity.FileEntity;
import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.error.provider.OperationErrorTypeProvider;
import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfoUtil;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadInputResolver;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadOutputResolver;
import com.cloudblue.connect.internal.metadata.resource.upload.ResourceFileUploadTypeKeysResolver;
import org.mule.runtime.api.meta.model.display.PathModel;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.error.Throws;
import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.metadata.TypeResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Path;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import static org.mule.runtime.extension.api.annotation.param.MediaType.APPLICATION_JSON;

@Throws(OperationErrorTypeProvider.class)
public class UploadResourceFileOperation extends BaseFileOperation {

    /***
     * File to upload.
     */
    @Parameter
    @DisplayName("File Path")
    @Path(type = PathModel.Type.FILE, location = PathModel.Location.EXTERNAL)
    @Expression
    @Placement(order = 3)
    String file;

    /**
     * Upload action identifier for a resource.
     */
    @Parameter
    @ParameterGroup(name = "Resource Action")
    @MetadataKeyId(ResourceFileUploadTypeKeysResolver.class)
    ActionIdentifier actionIdentifier;


    private FileEntity buildPayload(ActionInfo actionInfo, Map<String, Object> params) {
        FileEntity fileEntity = new FileEntity()
                .addFile(actionInfo.getFileName(), new File(file));

        actionInfo.getFormAttributes()
                .forEach(attributes -> fileEntity
                        .addValue(attributes.getField(),
                                (String) params.get(attributes.getField())));

        return fileEntity;
    }

    /**
     *
     * The operation to upload a resource file for a resource on CloudBlue Connect.
     *
     * @param connection the connection required to execute the operation.
     * @param uploadResourceFileParameter the resource Ids needed to perform the resource file upload.
     * @return Json representation of resource file upload response (sometimes can be blank) as a payload
     * and {@link CBCResponseAttributes} for operation http headers.
     */
    @MediaType(value = APPLICATION_JSON, strict = false)
    @DisplayName("Upload File")
    @OutputResolver(output = ResourceFileUploadOutputResolver.class)
    public Result<InputStream, CBCResponseAttributes> uploadFile(
            @Connection CBCConnection connection,
            @TypeResolver(ResourceFileUploadInputResolver.class)
            @Content Map<String, Object> uploadResourceFileParameter) {

        CBCConnection.Query query = getQuery(connection, actionIdentifier.getResourceType(),
                actionIdentifier.getAction(), uploadResourceFileParameter);

        ActionInfo actionInfo = CollectionInfoUtil.getActionInfo(actionIdentifier.getResourceType(),
                actionIdentifier.getAction());


        return query.action(getAction(actionIdentifier.getResourceType(), actionIdentifier.getAction()),
                actionInfo.getMethod(),
                buildPayload(actionInfo, uploadResourceFileParameter));
    }
}

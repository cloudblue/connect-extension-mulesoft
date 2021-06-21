package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.parameters.ResourceType;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class GetResourcesOperation {
    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId
    private ResourceType resourceType;

    @MediaType(value = ANY, strict = false)
    @DisplayName("Get Resource")
    @OutputResolver(output = ResourceOutputResolver.class)
    public CBCEntity getResources(
            @Connection CBCConnection connection,
            @ParameterGroup(name="Resource ID") ResourceActionParameter getResourceParameter
    ) throws CBCException {
        return (CBCEntity) connection
                .newQ(resourceType.getObjectTypeRef())
                .collection(resourceType.getCollection(), getResourceParameter.getId())
                .get();
    }
}

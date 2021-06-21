package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.models.common.CBCEntity;
import com.cloudblue.connect.api.parameters.ResourceType;
import com.cloudblue.connect.internal.operations.connections.CBCConnection;

import org.mule.runtime.extension.api.annotation.metadata.MetadataKeyId;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.util.List;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

public class ListResourcesOperation extends BaseListOperation {

    @Parameter
    @DisplayName("Resource Type")
    @MetadataKeyId
    private ResourceType resourceType;

    @MediaType(value = ANY, strict = false)
    @DisplayName("List Resources")
    @OutputResolver(output = ResourceOutputResolver.class)
    public List<CBCEntity> listResources(
            @Connection CBCConnection connection
    ) throws CBCException {
        Client.Q q = connection
                .newQ(resourceType.getListTypeRef())
                .collection(resourceType.getCollection());
        resolve(q);
        return (List<CBCEntity>) q.get();
    }
}

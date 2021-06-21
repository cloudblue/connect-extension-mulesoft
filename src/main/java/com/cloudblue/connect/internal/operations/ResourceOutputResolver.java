package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.parameters.ResourceType;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

public class ResourceOutputResolver implements OutputTypeResolver<ResourceType> {
    @Override
    public String getResolverName() {
        return "ResourceOutputResolver";
    }

    @Override
    public MetadataType getOutputType(
            MetadataContext context, ResourceType key
    ) throws MetadataResolvingException, ConnectionException {
        return context.getTypeLoader().load(key.getClazz());
    }

    @Override
    public String getCategoryName() {
        return "ResourceOutput";
    }
}

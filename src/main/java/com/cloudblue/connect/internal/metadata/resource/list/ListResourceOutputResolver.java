/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.resource.list;

import com.cloudblue.connect.internal.metadata.resource.ResourceOutputResolver;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

public class ListResourceOutputResolver
        extends ResourceOutputResolver implements OutputTypeResolver<String> {

    @Override
    public String getResolverName() {
        return "ListResourceOutputResolver";
    }

    @Override
    public MetadataType getOutputType(MetadataContext context, String resourceType)
            throws MetadataResolvingException, ConnectionException {
        return getOutputType(context, resourceType, Action.LIST.name());
    }
}

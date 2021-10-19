/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.resource.upload;

import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.metadata.Constants;
import com.cloudblue.connect.internal.metadata.resource.ResourceOutputResolver;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.OutputTypeResolver;

public class ResourceFileUploadOutputResolver
        extends ResourceOutputResolver implements OutputTypeResolver<ActionIdentifier> {
    @Override
    public String getResolverName() {
        return "ResourceFileUploadOutputResolver";
    }

    @Override
    public MetadataType getOutputType(MetadataContext context, ActionIdentifier key)
            throws MetadataResolvingException {
        return getOutputType(context, key.getResourceType(), key.getAction());
    }

    @Override
    public String getCategoryName() {
        return Constants.UPLOAD_RESOLVER_CATEGORY;
    }
}

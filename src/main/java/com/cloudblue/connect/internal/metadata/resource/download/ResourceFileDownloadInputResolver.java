/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.resource.download;

import com.cloudblue.connect.api.parameters.ActionIdentifier;
import com.cloudblue.connect.internal.metadata.Constants;
import com.cloudblue.connect.internal.metadata.resource.ResourceInputResolver;

import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.InputTypeResolver;

public class ResourceFileDownloadInputResolver
        extends ResourceInputResolver implements InputTypeResolver<ActionIdentifier> {
    @Override
    public String getResolverName() {
        return "ResourceFileDownloadInputResolver";
    }

    @Override
    public MetadataType getInputMetadata(MetadataContext context, ActionIdentifier key)
            throws MetadataResolvingException {
        return getInputMetadata(context, key.getResourceType(), key.getAction());
    }

    @Override
    public String getCategoryName() {
        return Constants.DOWNLOAD_RESOLVER_CATEGORY;
    }

}

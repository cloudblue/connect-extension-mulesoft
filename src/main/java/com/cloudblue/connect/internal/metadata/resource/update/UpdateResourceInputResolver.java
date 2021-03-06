/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.resource.update;

import com.cloudblue.connect.internal.metadata.Constants;
import com.cloudblue.connect.internal.metadata.resource.ResourceInputResolver;
import com.cloudblue.connect.internal.model.resource.Action;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.InputTypeResolver;

public class UpdateResourceInputResolver
        extends ResourceInputResolver implements InputTypeResolver<String> {
    @Override
    public String getResolverName() {
        return "UpdateResourceInputResolver";
    }

    @Override
    public MetadataType getInputMetadata(MetadataContext context, String resourceType)
            throws MetadataResolvingException {
        return getInputMetadata(context, resourceType, Action.UPDATE.name());
    }

    @Override
    public String getCategoryName() {
        return Constants.UPDATE_RESOLVER_CATEGORY;
    }
}

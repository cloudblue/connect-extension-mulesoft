/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata.resource.list;

import com.cloudblue.connect.internal.metadata.MetadataUtil;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataKeyBuilder;
import org.mule.runtime.api.metadata.MetadataResolvingException;
import org.mule.runtime.api.metadata.resolving.TypeKeysResolver;

import java.util.HashSet;
import java.util.Set;

public class ListResourceTypeKeysResolver implements TypeKeysResolver {

    @Override
    public Set<MetadataKey> getKeys(MetadataContext context)
            throws MetadataResolvingException, ConnectionException {
        Set<MetadataKey> keys = new HashSet<>();
        for(String resourceType : MetadataUtil.getListResourceTypes())
            keys.add(MetadataKeyBuilder.newKey(resourceType).build());

        return keys;
    }

    @Override
    public String getResolverName() {
        return "ListResourceTypeKeysResolver";
    }

    @Override
    public String getCategoryName() {
        return "Resources";
    }
}

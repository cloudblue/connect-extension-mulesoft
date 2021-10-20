/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata.resource.create;

import com.cloudblue.connect.internal.metadata.CollectionInfoUtil;
import com.cloudblue.connect.internal.metadata.Constants;
import org.mule.runtime.api.metadata.MetadataContext;
import org.mule.runtime.api.metadata.MetadataKey;
import org.mule.runtime.api.metadata.MetadataKeyBuilder;
import org.mule.runtime.api.metadata.resolving.TypeKeysResolver;

import java.util.HashSet;
import java.util.Set;

public class CreateResourceTypeKeysResolver implements TypeKeysResolver {

    @Override
    public Set<MetadataKey> getKeys(MetadataContext context) {
        Set<MetadataKey> keys = new HashSet<>();
        for(String resourceType : CollectionInfoUtil.getCreateResourceTypes()) {
            MetadataKeyBuilder key = MetadataKeyBuilder.newKey(resourceType);
            for(String action : CollectionInfoUtil.getCreateActions(resourceType)){
                key.withChild(MetadataKeyBuilder.newKey(action).build());
            }
            keys.add(key.build());
        }

        return keys;
    }

    @Override
    public String getResolverName() {
        return "CreateResourceTypeKeysResolver";
    }

    @Override
    public String getCategoryName() {
        return Constants.CREATE_RESOLVER_CATEGORY;
    }
}

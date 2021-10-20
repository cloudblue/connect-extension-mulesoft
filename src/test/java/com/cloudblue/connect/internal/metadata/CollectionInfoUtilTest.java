/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.api.parameters.ResourceType;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CollectionInfoUtilTest {

    @Test
    public void testGetListResourceTypes() {
        List<String> listResourceTypes = CollectionInfoUtil.getListResourceTypes();

        assertEquals(23, listResourceTypes.size());
    }

    @Test
    public void testCreateListResourceTypes() {
        List<String> listResourceTypes = CollectionInfoUtil.getCreateResourceTypes();

        assertEquals(7, listResourceTypes.size());

        List<String> createActions = CollectionInfoUtil.getCreateActions(ResourceType.REQUEST.name());

        assertEquals(3, createActions.size());
    }
}

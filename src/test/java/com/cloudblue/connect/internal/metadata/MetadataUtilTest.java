/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MetadataUtilTest {

    @Test
    public void testGetListResourceTypes() {
        List<String> listResourceTypes = MetadataUtil.getListResourceTypes();

        assertEquals(24, listResourceTypes.size());
    }
}

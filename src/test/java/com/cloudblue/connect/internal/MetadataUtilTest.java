/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MetadataUtilTest {

    @Test
    public void testGetActionMetadata() {
        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata("ASSET", "GET");

        Assert.assertNotNull(actionMetadata);

        List<String> resourceTypes = MetadataUtil.getActionResourceTypes();

        Assert.assertNotNull(resourceTypes);
    }
}

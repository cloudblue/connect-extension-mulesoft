/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;

import org.junit.Test;

import org.mule.metadata.api.model.ObjectType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class BaseMetadataProviderTest extends MetadataProviderTest {
    @Override
    protected String getConfigFile() {
        return "unit-test-flows.xml";
    }

    @Test
    public void test() {
        ObjectType input = (ObjectType) getInputMetadata(
                "get_resource_operation_ut",
                1, null, null,
                "getResourceParameter");

        assertNotNull(input);
        assertThat(input.getFields().size(), equalTo(1));
        assertFieldOfType(input, Keys.REQUEST_ID.getField(), typeBuilder.stringType().build());
        assertFieldRequirement(input, Keys.REQUEST_ID.getField(), true);
    }
}

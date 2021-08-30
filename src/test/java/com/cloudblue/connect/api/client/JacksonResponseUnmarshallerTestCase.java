/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.client;

import com.cloudblue.connect.api.clients.parsers.ResponseUnmarshaller;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.api.models.subscription.CBCRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JacksonResponseUnmarshallerTestCase {

    ResponseUnmarshaller unmarshaller = null;

    @Before
    public void setUp() {
        unmarshaller = new JacksonResponseUnmarshaller();
    }

    @Test
    public void testUnmarshalJsonArrayAsString() throws Exception {
        String value = "[{'key': 'value'}]";
        String returnValue = unmarshaller.unmarshal(value, String.class);

        Assert.assertEquals(value, returnValue);
    }
}

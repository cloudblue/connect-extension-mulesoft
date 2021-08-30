/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.client;

import com.cloudblue.connect.api.clients.BaseClient;
import com.cloudblue.connect.api.clients.Config;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.internal.error.exception.CBCException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpRequestBase;
import org.junit.Assert;
import org.junit.Test;

public class BaseClientTestCase {
    @Test
    public void testGetHttpRequest() throws ClassNotFoundException {
        BaseClient baseClient = new BaseClient(
                new Config(
                        "http://example.com/",
                        "TOKEN-001",
                        20000
                )
        );
        String url = "test/url";
        HttpMethod[] httpMethods = {
                HttpMethod.DELETE,
                HttpMethod.PATCH,
                HttpMethod.INVALID,
        };

        for (HttpMethod method : httpMethods) {
            try {
                HttpRequestBase httpRequest = baseClient.getHttpRequest(method, url);
                String methodName = method.name().toLowerCase();
                Class clazz = Class.forName("org.apache.http.client.methods.Http" + StringUtils.capitalize(methodName));

                Assert.assertTrue(clazz.isAssignableFrom(httpRequest.getClass()));
            } catch (CBCException e) {
                Assert.assertEquals(HttpMethod.INVALID, method);
            }
        }
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.common;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.parameters.Embeddable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResourceMultiAction extends ResourceActionParameter implements Embeddable {
    protected Map<String, String> payload = new HashMap<>();

    public abstract String action();

    public void addValue(String key, String value) {
        payload.put(key, value);
    }

    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }
}

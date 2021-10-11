/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.api.parameters;

import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.Serializable;

public class CBCResponseAttributes implements Serializable {

    private static final long serialVersionUID = -5179584736827635463L;

    private final MultiMap<String, String> headers;
    private final int statusCode;
    private final String reasonPhrase;

    public CBCResponseAttributes(HttpResponse response) {
        this.headers = response.getHeaders();
        this.statusCode = response.getStatusCode();
        this.reasonPhrase = response.getReasonPhrase();
    }

    public MultiMap<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return "CBCResponseAttributes{" +
                "headers=" + headers +
                ", statusCode=" + statusCode +
                ", reasonPhrase='" + reasonPhrase + '\'' +
                '}';
    }
}

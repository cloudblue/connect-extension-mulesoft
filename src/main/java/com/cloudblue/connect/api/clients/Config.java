/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.clients;

public class Config {
    private final String host;
    private final String token;
    private final int connectionTimeout;

    public Config(String host, String token, int connectionTimeout) {
        this.host = host;
        this.token = token;
        this.connectionTimeout = connectionTimeout;
    }

    public String getHost() {
        return host;
    }

    public String getToken() {
        return token;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}

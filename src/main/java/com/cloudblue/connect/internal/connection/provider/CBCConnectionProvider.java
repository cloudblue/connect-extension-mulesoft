/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.connection.provider;


import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.lifecycle.Startable;
import org.mule.runtime.api.lifecycle.Stoppable;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.RefName;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.display.Summary;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.proxy.ProxyConfig;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class CBCConnectionProvider implements CachedConnectionProvider<CBCConnection>, Startable, Stoppable {

    public static final class ConnectionParams {

        @Parameter
        @Expression
        @Optional(defaultValue = "https://api.connect.cloud.im/public/v1")
        @DisplayName("API Endpoint")
        @Summary("API endpoint")
        private String endpoint;

        @Parameter
        @Expression
        @DisplayName("Authentication Token")
        @Summary("API authentication token")
        private String token;

        @Parameter
        @Optional(defaultValue = "5")
        @Placement(tab = Placement.ADVANCED_TAB, order = 1)
        @DisplayName("API Timeout Value")
        @Summary("API call timeout timeout value")
        private int connectionTimeout;

        @Parameter
        @Optional(defaultValue = "SECONDS")
        @Placement(tab = Placement.ADVANCED_TAB, order = 2)
        @DisplayName("API Timeout Unit")
        @Summary("Time unit to be used in the Timeout configurations")
        private TimeUnit connectionTimeoutUnit;

        @Parameter
        @Optional
        @Summary("Proxy configuration for outbound connections")
        @DisplayName("Authentication Token")
        @Placement(tab = "Proxy")
        private ProxyConfig proxyConfig;

        public int getMilSecTimeout() {
            return (int) connectionTimeoutUnit.toMillis(connectionTimeout);
        }

        public String getEndpoint() {
            return endpoint;
        }

        public String getToken() {
            return token;
        }

        public ProxyConfig getProxyConfig() {
            return proxyConfig;
        }
    }

    @RefName
    private String configName;

    @Inject
    private HttpService httpService;

    private HttpClient httpClient;

    @ParameterGroup(name = ParameterGroup.CONNECTION)
    private ConnectionParams connectionParams;

    @Override
    public CBCConnection connect() throws ConnectionException {
        return new CBCConnection(this.httpClient, connectionParams);
    }

    @Override
    public void disconnect(CBCConnection connection) {
        // Nothing to do
    }

    @Override
    public void start() {
        httpClient = httpService.getClientFactory()
                .create(new HttpClientConfiguration.Builder()
                        .setProxyConfig(connectionParams.proxyConfig)
                        .setName(configName)
                        .setConnectionIdleTimeout(connectionParams.getMilSecTimeout())
                        .build());
        httpClient.start();
    }

    @Override
    public void stop() {
        if (httpClient != null)
            httpClient.stop();
    }

    @Override
    public ConnectionValidationResult validate(CBCConnection connection) {
        return ConnectionValidationResult.success();
    }
}

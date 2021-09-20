/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.connection.provider;

import com.cloudblue.connect.internal.connection.CBCConnection;
import com.cloudblue.connect.internal.connection.WebhookConnection;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.api.lifecycle.Lifecycle;
import org.mule.runtime.api.notification.NotificationListenerRegistry;
import org.mule.runtime.api.scheduler.SchedulerConfig;
import org.mule.runtime.api.scheduler.SchedulerService;
import org.mule.runtime.api.tls.TlsContextFactory;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.RefName;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.server.HttpServerConfiguration;
import org.mule.runtime.http.api.server.ServerCreationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Field;

import static java.lang.String.format;
import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.core.api.lifecycle.LifecycleUtils.initialiseIfNeeded;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.SECURITY_TAB;

@Alias("listener")
public class WebhookConnectionProvider implements CachedConnectionProvider<WebhookConnection>, Lifecycle {
    private static final Logger logger = LoggerFactory.getLogger(WebhookConnectionProvider.class);

    public static final class ListenerParams {

        @Parameter
        @Optional(defaultValue = "HTTP")
        @Expression(NOT_SUPPORTED)
        @DisplayName("Protocol")
        @Placement(order = 1)
        private HttpConstants.Protocol protocol;

        @Parameter
        @Example("For example 0.0.0.0")
        @Expression(NOT_SUPPORTED)
        @DisplayName("Host")
        @Placement(order = 2)
        private String host;

        @Parameter
        @Example("8088")
        @Expression(NOT_SUPPORTED)
        @DisplayName("Port")
        @Placement(order = 3)
        private Integer port;


        @Parameter
        @Example("https://myintegration.com")
        @Expression(NOT_SUPPORTED)
        @DisplayName("Webhook Binding URL")
        @Placement(order = 4)
        private String webhookBindingUrl;

        public HttpConstants.Protocol getProtocol() {
            return protocol;
        }

        public String getHost() {
            return host;
        }

        public Integer getPort() {
            return port;
        }

        public String getWebhookBindingUrl() {
            return webhookBindingUrl;
        }
    }

    @RefName
    private String configName;

    @Parameter
    @ParameterGroup(name = "Webhook Listener Details")
    private ListenerParams listenerParams;

    @Parameter
    @ParameterGroup(name = "Connect API Endpoint Details")
    private CBCConnectionProvider.ConnectionParams connectionParams;

    @Parameter
    @Optional
    @Expression(NOT_SUPPORTED)
    @DisplayName("TLS Configuration")
    @Placement(tab = SECURITY_TAB)
    private TlsContextFactory tlsContext;

    @Inject
    private HttpService httpService;

    @Inject
    private SchedulerService schedulerService;

    @Inject
    private NotificationListenerRegistry notificationListenerRegistry;

    private WebhookConnection webhookConnection;
    private HttpClient httpClient;

    @Override
    public WebhookConnection connect() throws ConnectionException {
        return webhookConnection;
    }

    @Override
    public void disconnect(WebhookConnection webhookConnection) {
        //Webhook listener is sharable, do not do anything
    }

    @Override
    public ConnectionValidationResult validate(WebhookConnection webhookConnection) {
        try {
            webhookConnection.isConnected();

            return ConnectionValidationResult.success();
        } catch (ConnectionException e) {
            logger.error("Error during validating connection.", e);
            return ConnectionValidationResult.failure(e.getMessage(), e);
        }
    }

    @Override
    public void dispose() {
        webhookConnection.getHttpServer().dispose();
    }

    private void validate() throws InitialisationException {
        if (listenerParams.protocol == HttpConstants.Protocol.HTTPS && tlsContext == null) {
            throw new InitialisationException(
                    createStaticMessage(
                            "Webhook listener configured protocol is HTTPS but there's" +
                                    " no TlsContext configured for configuration '%s'.",
                            configName
                    ),
                    this
            );
        }
        if (tlsContext != null && !tlsContext.isKeyStoreConfigured()) {
            throw new InitialisationException(
                    createStaticMessage(
                            "KeyStore must be configured for server side SSL in configuration '%s'.",
                            configName
                    ),
                    this
            );
        }
    }

    @Override
    public void initialise() throws InitialisationException {
        if (listenerParams.port == null) {
            listenerParams.port = listenerParams.protocol.getDefaultPort();
        }
        webhookConnection = new WebhookConnection();

        validate();

        if (tlsContext != null) {
            initialiseIfNeeded(tlsContext);
        }

        HttpServerConfiguration serverConfiguration = getServerConfiguration();

        try {
            webhookConnection.setHttpServer(httpService.getServerFactory().create(serverConfiguration));
            webhookConnection.setServerEndpoint(listenerParams.getWebhookBindingUrl());
        } catch (ServerCreationException e) {
            throw new InitialisationException(
                    createStaticMessage("Not able to create Webhook Listener with configuration %s", configName),
                    e,
                    this
            );
        }

        httpClient = httpService.getClientFactory()
                .create(new HttpClientConfiguration.Builder()
                        .setProxyConfig(connectionParams.getProxyConfig())
                        .setName(configName)
                        .setConnectionIdleTimeout(connectionParams.getMilSecTimeout())
                        .build());

        webhookConnection.setCbcConnection(new CBCConnection(this.httpClient, connectionParams));

    }

    @Override
    public void start() throws MuleException {
        try {

            webhookConnection.getHttpServer().start();
            httpClient.start();

        } catch (IOException e) {
            throw new ConnectionException(
                    format("Not able to start Webhook Listener with configuration %s", configName),
                    e);
        }
    }

    @Override
    public void stop() throws MuleException {
        if (webhookConnection.getHttpServer() != null &&
                !webhookConnection.getHttpServer().isStopped()) {
            webhookConnection.getHttpServer().stop();
        }

        if (httpClient != null)
            httpClient.stop();
    }

    private HttpServerConfiguration getServerConfiguration() {
        HttpServerConfiguration.Builder builder = new HttpServerConfiguration.Builder()
                .setHost(listenerParams.getHost())
                .setPort(listenerParams.getPort())
                .setName(configName);

        if (listenerParams.getProtocol() == HttpConstants.Protocol.HTTPS)
            builder = builder.setTlsContextFactory(tlsContext);

        if (useIOScheduler()) {
            builder.setSchedulerSupplier(
                    () -> schedulerService.ioScheduler(
                            SchedulerConfig.config().withName(getSchedulerName(listenerParams))
                    )
            );
        }

        return builder.build();
    }

    private boolean useIOScheduler() {
        try {
            Field result = httpService.getServerFactory().getClass().getDeclaredField("USE_IO_SCHEDULER");
            return result.getBoolean(httpService);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            logger.info("Error during using IO scheduler.", e);
            return false;
        }
    }

    private String getSchedulerName(ListenerParams listenerParams) {
        return String.format(
                "CBC-Connect-Webhook-listener-scheduler-io[%s://%s:%d]",
                listenerParams.getProtocol().toString().toLowerCase(),
                listenerParams.getHost(),
                listenerParams.getPort()
        );
    }
}

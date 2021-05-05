package com.cloudblue.connect.internal.sources.connections;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import com.cloudblue.connect.internal.operations.connections.CBCConnectionProvider;

import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.exception.DefaultMuleException;
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
import org.mule.runtime.http.api.server.HttpServerConfiguration;
import org.mule.runtime.http.api.server.ServerCreationException;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.reflect.Field;

import static java.lang.String.format;
import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.core.api.lifecycle.LifecycleUtils.initialiseIfNeeded;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.SECURITY_TAB;

@Alias("listener")
public class WebhookListenerProvider implements CachedConnectionProvider<WebhookListener>, Lifecycle {
    public static final class ListenerParams {

        @Parameter
        @Optional(defaultValue = "HTTP")
        @Expression(NOT_SUPPORTED)
        @Placement(order = 1)
        private HttpConstants.Protocol protocol;

        @Parameter
        @Example("0.0.0.0")
        @Expression(NOT_SUPPORTED)
        @Placement(order = 2)
        private String host;

        @Parameter
        @Example("8088")
        @Expression(NOT_SUPPORTED)
        @Placement(order = 3)
        private Integer port;


        @Parameter
        @Example("https://myintegration.com")
        @Expression(NOT_SUPPORTED)
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

    private WebhookListener webhookListener;

    @Override
    public WebhookListener connect() throws ConnectionException {
        return webhookListener;
    }

    @Override
    public void disconnect(WebhookListener httpServer) {
        //Webhook listener is sharable, do not do anything
    }

    @Override
    public ConnectionValidationResult validate(WebhookListener httpServer) {
        try {
            webhookListener.isConnected();

            return ConnectionValidationResult.success();
        } catch (ConnectionException e) {
            return ConnectionValidationResult.failure(e.getMessage(), e);
        }
    }

    @Override
    public void dispose() {
        webhookListener.getHttpServer().dispose();
    }

    @Override
    public void initialise() throws InitialisationException {
        if (listenerParams.port == null) {
            listenerParams.port = listenerParams.protocol.getDefaultPort();
        }
        webhookListener = new WebhookListener();

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

        if (tlsContext != null) {
            initialiseIfNeeded(tlsContext);
        }

        HttpServerConfiguration serverConfiguration = getServerConfiguration();

        try {
            webhookListener.setHttpServer(httpService.getServerFactory().create(serverConfiguration));
            webhookListener.setServerEndpoint(listenerParams.getWebhookBindingUrl());
        } catch (ServerCreationException e) {
            throw new InitialisationException(
                    createStaticMessage("Not able to create Webhook Listener with configuration %s", configName),
                    e,
                    this
            );
        }

        webhookListener.setCbcConnection(new CBCConnection(this.connectionParams));

    }

    @Override
    public void start() throws MuleException {
        try {
            webhookListener.getHttpServer().start();
        } catch (IOException e) {
            throw new DefaultMuleException(
                    new ConnectionException(
                            format("Not able to start Webhook Listener with configuration %s", configName),
                            e
                    )
            );
        }
    }

    @Override
    public void stop() throws MuleException {
        if (webhookListener.getHttpServer() != null &&
                !webhookListener.getHttpServer().isStopped()) {
            webhookListener.getHttpServer().stop();
        }
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

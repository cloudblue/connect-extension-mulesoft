/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.connection;

import org.junit.Test;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.ServerAddress;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WebhookConnectionTest {

    @Test
    public void testIsConnectedTrue() throws ConnectionException {
        CBCConnection cbcConnection = mock(CBCConnection.class);
        when(cbcConnection.isConnected()).thenReturn(true);

        HttpServer mockHttpServer = mock(HttpServer.class);
        when(mockHttpServer.isStopped()).thenReturn(false);
        when(mockHttpServer.isStopping()).thenReturn(false);

        WebhookConnection webhookConnection = new WebhookConnection();
        webhookConnection.setHttpServer(mockHttpServer);
        webhookConnection.setCbcConnection(cbcConnection);

        assertTrue(webhookConnection.isConnected());
    }

    @Test(expected = ConnectionException.class)
    public void testIsConnectedServerStopped() throws ConnectionException {
        CBCConnection cbcConnection = mock(CBCConnection.class);
        when(cbcConnection.isConnected()).thenReturn(true);

        ServerAddress serverAddress = mock(ServerAddress.class);
        when(serverAddress.getIp()).thenReturn("127.0.0.1");
        when(serverAddress.getPort()).thenReturn(8080);

        HttpServer mockHttpServer = mock(HttpServer.class);
        when(mockHttpServer.isStopped()).thenReturn(true);
        when(mockHttpServer.isStopping()).thenReturn(false);
        when(mockHttpServer.getServerAddress()).thenReturn(serverAddress);

        WebhookConnection webhookConnection = new WebhookConnection();
        webhookConnection.setHttpServer(mockHttpServer);
        webhookConnection.setCbcConnection(cbcConnection);

        webhookConnection.isConnected();
    }

    @Test(expected = ConnectionException.class)
    public void testIsConnectedServerStopping() throws ConnectionException {
        CBCConnection cbcConnection = mock(CBCConnection.class);
        when(cbcConnection.isConnected()).thenReturn(true);

        ServerAddress serverAddress = mock(ServerAddress.class);
        when(serverAddress.getIp()).thenReturn("127.0.0.1");
        when(serverAddress.getPort()).thenReturn(8080);

        HttpServer mockHttpServer = mock(HttpServer.class);
        when(mockHttpServer.isStopped()).thenReturn(false);
        when(mockHttpServer.isStopping()).thenReturn(true);
        when(mockHttpServer.getServerAddress()).thenReturn(serverAddress);

        WebhookConnection webhookConnection = new WebhookConnection();
        webhookConnection.setHttpServer(mockHttpServer);
        webhookConnection.setCbcConnection(cbcConnection);

        webhookConnection.isConnected();
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.ServerAddress;


public class WebhookConnection {
    private HttpServer httpServer;
    private CBCConnection cbcConnection;

    private String serverEndpoint;

    public boolean isConnected() throws ConnectionException {
        if (httpServer.isStopped() || httpServer.isStopping()) {
            ServerAddress serverAddress = httpServer.getServerAddress();
            throw new ConnectionException(
                    String.format(
                            "Webhook Listener on host %s and port %s is not able to start.",
                            serverAddress.getIp(),
                            serverAddress.getPort()
                    )
            );
        }

        return true;
    }

    public HttpServer getHttpServer() {
        return httpServer;
    }

    public void setHttpServer(HttpServer httpServer) {
        this.httpServer = httpServer;
    }

    public CBCConnection getCbcConnection() {
        return cbcConnection;
    }

    public void setCbcConnection(CBCConnection cbcConnection) {
        this.cbcConnection = cbcConnection;
    }

    public String getServerEndpoint() {
        return serverEndpoint;
    }

    public void setServerEndpoint(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }
}

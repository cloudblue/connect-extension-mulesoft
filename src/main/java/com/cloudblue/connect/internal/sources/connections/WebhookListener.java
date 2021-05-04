package com.cloudblue.connect.internal.sources.connections;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.ServerAddress;


public class WebhookListener {
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

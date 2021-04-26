package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.operations.connections.CBCConnection;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.ServerAddress;


public class WebhookListener {
    private HttpServer httpServer;
    private CBCConnection cbcConnection;

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

//        if (!cbcConnection.isConnected()) {
//            throw new ConnectionException(
//                    "Webhook Listener is not able to access Cloudblue Connect APIs with specified configuration."
//            );
//        }

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
}

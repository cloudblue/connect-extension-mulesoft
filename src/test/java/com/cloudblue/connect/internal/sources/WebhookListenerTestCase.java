/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.connection.WebhookListener;
import org.junit.Test;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.server.HttpServer;
import org.mule.runtime.http.api.server.RequestHandler;
import org.mule.runtime.http.api.server.RequestHandlerManager;
import org.mule.runtime.http.api.server.ServerAddress;

import java.io.IOException;
import java.util.Collection;

public class WebhookListenerTestCase {
    @Test(expected = ConnectionException.class)
    public void testIsConnectedError() throws ConnectionException {
        WebhookListener listener = new WebhookListener();
        listener.setHttpServer(new HttpServer() {
            @Override
            public HttpServer start() throws IOException {
                return null;
            }

            @Override
            public HttpServer stop() {
                return null;
            }

            @Override
            public void dispose() {

            }

            @Override
            public ServerAddress getServerAddress() {
                return new ServerAddress() {
                    @Override
                    public int getPort() {
                        return 8080;
                    }

                    @Override
                    public String getIp() {
                        return "127.0.0.1";
                    }

                    @Override
                    public boolean overlaps(ServerAddress serverAddress) {
                        return false;
                    }
                };
            }

            @Override
            public HttpConstants.Protocol getProtocol() {
                return null;
            }

            @Override
            public boolean isStopping() {
                return false;
            }

            @Override
            public boolean isStopped() {
                return true;
            }

            @Override
            public RequestHandlerManager addRequestHandler(Collection<String> methods, String path, RequestHandler requestHandler) {
                return null;
            }

            @Override
            public RequestHandlerManager addRequestHandler(String path, RequestHandler requestHandler) {
                return null;
            }
        });

        listener.isConnected();
    }
}

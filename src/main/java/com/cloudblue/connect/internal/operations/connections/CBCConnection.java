package com.cloudblue.connect.internal.operations.connections;

import com.cloudblue.connect.api.clients.Client;


public final class CBCConnection extends Client {

    public CBCConnection(CBCConnectionProvider.ConnectionParams config) {
        super(config.getConfig());
    }

}

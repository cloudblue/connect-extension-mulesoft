package com.cloudblue.connect.internal.connections;

import com.cloudblue.connect.api.models.CBCAccount;
import com.cloudblue.connect.internal.connections.clients.CBCClient;
import com.cloudblue.connect.internal.connections.constants.HttpMethod;
import com.cloudblue.connect.api.exceptions.CBCException;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

import org.mule.runtime.api.connection.ConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CBCConnection extends CBCClient {
        
    private final Logger LOGGER = LoggerFactory.getLogger(CBCConnection.class);

    public CBCConnection(CBCConnectionProvider.ConnectionParams config) {
        super(config);
    }

    public boolean isConnected() throws Exception {
        
        TypeReference<List<CBCAccount>> listAccountRefs =new TypeReference<List<CBCAccount>>() {};
        
        try {
            exchange("accounts", null, HttpMethod.GET, null, listAccountRefs);
        } catch(CBCException ex) {
            LOGGER.error("Error during testing connection", ex);
            throw new ConnectionException(
                    "Error connecting to the server: Error Code "
                    + ex.getErrorCode()
                    + "~" + ex.getDetailedErrormessage()
            );
        }
        return true;
    }

}

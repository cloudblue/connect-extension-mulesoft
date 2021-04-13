package com.cloudblue.connect.internal.connections;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.models.CBCTenant;
import com.cloudblue.connect.api.exceptions.CBCException;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import org.mule.runtime.api.connection.ConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CBCConnection extends Client {
        
    private final Logger LOGGER = LoggerFactory.getLogger(CBCConnection.class);

    public CBCConnection(CBCConnectionProvider.ConnectionParams config) {
        super(config.getConfig());
    }

    public boolean isConnected() throws Exception {

        try {
            newQ(new TypeReference<ArrayList<CBCTenant>>() {})
                    .collection("accounts")
                    .limit(1)
                    .get();
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

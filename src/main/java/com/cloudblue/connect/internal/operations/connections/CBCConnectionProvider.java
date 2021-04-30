package com.cloudblue.connect.internal.operations.connections;


import com.cloudblue.connect.api.clients.Config;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.RefName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CBCConnectionProvider implements PoolingConnectionProvider<CBCConnection> {

    private final Logger logger = LoggerFactory.getLogger(CBCConnectionProvider.class);

    public static final class ConnectionParams {

        @Parameter
        @Expression
        @Optional(defaultValue = "https://api.connect.cloud.im/public/v1")
        private String endpoint;

        @Parameter
        @Expression
        private String token;

        @Parameter
        @Optional(defaultValue = "20000")
        private int connectionTimeout;

        public Config getConfig() {
            return new Config(endpoint, token, connectionTimeout);
        }
    }

    @RefName
    private String configName;

    @ParameterGroup(name = ParameterGroup.CONNECTION)
    private ConnectionParams connectionParams;

    @Override
    public CBCConnection connect() throws ConnectionException {
        return new CBCConnection(this.connectionParams);
    }

    @Override
    public void disconnect(CBCConnection connection) {
        try {
            connection.close();
        } catch (Exception e) {
            logger.error("Error while disconnecting Connect Connection: " + e.getMessage(), e);
        }
    }

    @Override
    public ConnectionValidationResult validate(CBCConnection connection) {
        return ConnectionValidationResult.success();
    }
}

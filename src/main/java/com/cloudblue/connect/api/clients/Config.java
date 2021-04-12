package com.cloudblue.connect.api.clients;

public class Config {
    private final String host;
    private final String token;
    private final int connectionTimeout;

    public Config(String host, String token, int connectionTimeout) {
        this.host = host;
        this.token = token;
        this.connectionTimeout = connectionTimeout;
    }

    public String getHost() {
        return host;
    }

    public String getToken() {
        return token;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}

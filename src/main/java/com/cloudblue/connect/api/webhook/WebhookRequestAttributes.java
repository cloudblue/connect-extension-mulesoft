package com.cloudblue.connect.api.webhook;

import java.io.Serializable;

public class WebhookRequestAttributes implements Serializable {
    private static final long serialVersionUID = -4592240902896781173L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

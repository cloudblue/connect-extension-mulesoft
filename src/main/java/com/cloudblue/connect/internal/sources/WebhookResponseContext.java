package com.cloudblue.connect.internal.sources;

import org.mule.runtime.http.api.server.async.HttpResponseReadyCallback;

public class WebhookResponseContext {

    private HttpResponseReadyCallback responseCallback;

    public HttpResponseReadyCallback getResponseCallback() {
        return responseCallback;
    }

    public void setResponseCallback(HttpResponseReadyCallback responseCallback) {
        this.responseCallback = responseCallback;
    }
}

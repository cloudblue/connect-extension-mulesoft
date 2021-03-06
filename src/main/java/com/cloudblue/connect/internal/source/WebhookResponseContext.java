/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.source;

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

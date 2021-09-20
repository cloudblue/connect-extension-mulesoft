/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.config;

import com.cloudblue.connect.internal.source.RequestValidationListener;
import com.cloudblue.connect.internal.source.TCRValidationListener;
import com.cloudblue.connect.internal.source.WebhookListener;
import com.cloudblue.connect.internal.connection.provider.WebhookConnectionProvider;
import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;


@Configuration(name = "webhookConfig")
@ConnectionProviders(WebhookConnectionProvider.class)
@Sources({WebhookListener.class, RequestValidationListener.class, TCRValidationListener.class})
public class CBCWebhookConfig {

    @Parameter
    @Placement(order = 1)
    @Optional
    private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    private void append(StringBuilder pathSuffix, String sniffed) {
        String modifiedSniffed = sniffed;
        if (sniffed!= null && !sniffed.isEmpty()) {
            if (!sniffed.startsWith("/"))
                modifiedSniffed = "/" + sniffed;
            if (sniffed.endsWith("/"))
                modifiedSniffed = sniffed.substring(0, sniffed.lastIndexOf("/"));

            pathSuffix.append(modifiedSniffed);
        }
    }

    public String getFullListenerPath(String path, String webhookType) {
        StringBuilder pathSuffix = new StringBuilder();

        append(pathSuffix, basePath);
        append(pathSuffix, path);
        append(pathSuffix, webhookType);

        return pathSuffix.toString();
    }
}

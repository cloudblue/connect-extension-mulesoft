package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.sources.WebhookListenerProvider;
import com.cloudblue.connect.internal.sources.WebhookSource;

import org.mule.runtime.extension.api.annotation.Configuration;
import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;


@Configuration(name = "webhookConfig")
@ConnectionProviders(WebhookListenerProvider.class)
@Sources(WebhookSource.class)
public class CBCWebhookConfig {

    @Parameter
    @Placement(order = 1)
    @Optional
    private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public String getFullListenerPath(String listenerPath) {
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.lastIndexOf("/"));
        }

        if (!listenerPath.startsWith("/")) {
            listenerPath = "/" + listenerPath;
        }
        return basePath + listenerPath;
    }
}

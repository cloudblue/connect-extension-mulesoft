package com.cloudblue.connect.internal.sources;

import com.cloudblue.connect.internal.sources.connections.WebhookListenerProvider;
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

    private void append(StringBuilder pathSuffix, String sniffed) {

        if (sniffed!= null && !sniffed.isEmpty()) {
            if (!sniffed.startsWith("/"))
                sniffed = "/" + sniffed;
            if (sniffed.endsWith("/"))
                sniffed = sniffed.substring(0, sniffed.lastIndexOf("/"));

            pathSuffix.append(sniffed);
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

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
@Sources({WebhookSource.class, RequestValidationSource.class, TCRValidationSource.class})
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

package com.cloudblue.connect.api.clients.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLBuilder {
    private List<String> collections;
    private Map<String, String> idMap;

    public URLBuilder() {
        this.collections = new ArrayList<>();
        this.idMap = new HashMap<>();
    }

    public URLBuilder addCollection(String collectionName, String resourceId) {
        assert collectionName != null && !collectionName.isEmpty();

        collections.add(collectionName);

        if (resourceId != null) {
            idMap.put(collectionName, resourceId);
        }

        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();

        if (!collections.isEmpty()) {
            for (String collection: collections) {
                builder.append("/");
                builder.append(collection);

                String resourceId = idMap.get(collection);

                if (resourceId != null) {
                    builder.append("/");
                    builder.append(resourceId);
                }
            }
        }

        return builder.toString();
    }
}

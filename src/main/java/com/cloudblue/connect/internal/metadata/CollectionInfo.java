/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata;

import com.cloudblue.connect.internal.model.resource.Action;

import java.util.EnumMap;
import java.util.Map;

public class CollectionInfo {
    private String collection;
    private Keys id;

    private boolean isSubCollection = false;
    private Keys parentId;
    private String parentCollection;

    private String schema;

    private final EnumMap<Action, ActionInfo> actionMetadata = new EnumMap<>(Action.class);

    public CollectionInfo collection(String collection) {
        this.collection = collection;

        return this;
    }

    public CollectionInfo id(Keys id) {
        this.id = id;

        return this;
    }

    public CollectionInfo isSubCollection(boolean isSubCollection) {
        this.isSubCollection = isSubCollection;

        return this;
    }

    public CollectionInfo parentId(Keys parentId) {
        this.parentId = parentId;

        return this;
    }

    public CollectionInfo parentCollection(String parentCollection) {
        this.parentCollection = parentCollection;

        return this;
    }

    public CollectionInfo addActionMetaData(Action action, ActionInfo actionInfo) {
        if (actionInfo.getOutput() == null) {
            actionInfo.output(schema);
        }
        this.actionMetadata.put(action, actionInfo);

        return this;
    }

    public CollectionInfo schema(String schema) {
        this.schema = schema;

        return this;
    }

    public CollectionInfo includeListAction(Keys... filters) {
        this.addActionMetaData(Action.LIST,
                new ActionInfo()
                        .output(schema)
                        .input(isSubCollection? new ParentMetadataProvider(): null)
                        .filter(filters));

        return this;
    }

    public CollectionInfo includeGetAction(Keys... filters) {
        this.addActionMetaData(Action.GET,
                new ActionInfo()
                        .output(schema)
                        .input(new BaseMetadataProvider())
                        .filter(filters));

        return this;
    }

    public String getCollection() {
        return collection;
    }

    public Keys getId() {
        return id;
    }

    public boolean isSubCollection() {
        return isSubCollection;
    }

    public Keys getParentId() {
        return parentId;
    }

    public String getParentCollection() {
        return parentCollection;
    }

    public Map<Action, ActionInfo> getActionMetadata() {
        return actionMetadata;
    }

    public String getSchema() {
        return schema;
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.metadata;


import static org.mule.runtime.http.api.HttpConstants.Method;

public class ActionMetadata {
    private String output;
    private MetadataProvider input;
    private Method method = Method.POST;
    private String action;
    private boolean includePayload = true;
    private boolean collectionAction = false;

    public ActionMetadata output(String output) {
        this.output = output;
        return this;
    }

    public ActionMetadata input(MetadataProvider input) {
        this.input = input;
        return this;
    }

    public ActionMetadata method(Method method) {
        this.method = method;
        return this;
    }

    public ActionMetadata action(String action) {
        this.action = action;
        return this;
    }

    public ActionMetadata includePayload(boolean includePayload) {
        this.includePayload = includePayload;
        return this;
    }

    public ActionMetadata collectionAction(boolean collectionAction) {
        this.collectionAction = collectionAction;
        return this;
    }

    public String getOutput() {
        return output;
    }

    public MetadataProvider getInput() {
        return input;
    }

    public Method getMethod() {
        return method;
    }

    public String getAction() {
        return action;
    }

    public boolean isIncludePayload() {
        return includePayload;
    }

    public boolean isCollectionAction() {
        return collectionAction;
    }
}

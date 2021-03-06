/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.metadata;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mule.runtime.http.api.HttpConstants.Method;

public class ActionInfo {
    private String output;
    private MetadataProvider input;
    private Method method = Method.POST;
    private String action;
    private boolean includePayload = true;
    private boolean collectionAction = false;
    private final List<Keys> filters = new ArrayList<>();
    private final List<Keys> formAttributes = new ArrayList<>();
    private String fileName;

    public ActionInfo output(String output) {
        this.output = output;
        return this;
    }

    public ActionInfo input(MetadataProvider input) {
        this.input = input;
        return this;
    }

    public ActionInfo input(String input) {
        this.input = new JsonMetadataProvider(input);
        return this;
    }

    public ActionInfo method(Method method) {
        this.method = method;
        return this;
    }

    public ActionInfo action(String action) {
        this.action = action;
        return this;
    }

    public ActionInfo includePayload(boolean includePayload) {
        this.includePayload = includePayload;
        return this;
    }

    public ActionInfo collectionAction(boolean collectionAction) {
        this.collectionAction = collectionAction;
        return this;
    }

    public ActionInfo filter(Keys... filter) {
        this.filters.addAll(Arrays.asList(filter));

        return this;
    }

    public ActionInfo formAttributes(Keys... attributes) {
        this.formAttributes.addAll(Arrays.asList(attributes));

        return this;
    }

    public ActionInfo fileName(String fileName) {
        this.fileName = fileName;

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

    public List<Keys> getFilters() {
        return filters;
    }

    public List<Keys> getFormAttributes() {
        return formAttributes;
    }

    public String getFileName() {
        return fileName;
    }
}

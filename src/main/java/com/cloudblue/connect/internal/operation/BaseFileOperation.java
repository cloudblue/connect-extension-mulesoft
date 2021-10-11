/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.internal.metadata.ActionMetadata;
import com.cloudblue.connect.internal.metadata.MetadataUtil;

public class BaseFileOperation extends BaseResourceIdentifierOperation {

    protected String getAction(String resourceType, String action) {
        ActionMetadata actionMetadata = MetadataUtil.getActionMetadata(resourceType, action);

        String actionName = actionMetadata.getAction();

        if (actionName == null) {
            actionName = action;
        }

        return actionName.toLowerCase();
    }
}

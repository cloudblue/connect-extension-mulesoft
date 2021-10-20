/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.operation;

import com.cloudblue.connect.internal.metadata.ActionInfo;
import com.cloudblue.connect.internal.metadata.CollectionInfoUtil;

public class BaseFileOperation extends BaseResourceIdentifierOperation {

    protected String getAction(String resourceType, String action) {
        ActionInfo actionInfo = CollectionInfoUtil.getActionInfo(resourceType, action);

        String actionName = actionInfo.getAction();

        if (actionName == null) {
            actionName = action;
        }

        return actionName.toLowerCase();
    }
}

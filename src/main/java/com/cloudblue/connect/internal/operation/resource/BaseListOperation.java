  
/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.operation.resource;

import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;
import com.cloudblue.connect.internal.connection.CBCConnection;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class BaseListOperation {
    @Parameter
    @Optional
    Filter filter;

    @Parameter
    @Optional
    OrderBy orderBy;

    @Parameter
    @Optional(defaultValue = "100")
    Integer limit;

    @Parameter
    @Optional(defaultValue = "0")
    Integer offset;

    protected void resolve(CBCConnection.Q q) {

        if (orderBy != null){
            q.orderBy(orderBy.getProperties().toArray(new String[]{}));
        }

        if (filter != null) {
            q.filter(filter.toRQL());
        }

        q.limit(limit);
        q.offset(offset);
        q.listOperation(true);
    }
}
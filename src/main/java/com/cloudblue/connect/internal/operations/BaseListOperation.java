package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class BaseListOperation {
    @Parameter
    @Optional
    Filter filter;

    @Parameter
    @Optional
    OrderBy orderBy;

    protected void resolve(Client.Q q) {

        if (orderBy != null){
            q.orderBy(orderBy.getProperties().toArray(new String[]{}));
        }

        if (filter != null) {
            q.filter(filter.toRQL());
        }
    }
}

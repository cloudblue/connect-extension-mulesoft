  
package com.cloudblue.connect.internal.operations;

import com.cloudblue.connect.api.clients.Client;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.OrderBy;

import org.mule.runtime.extension.api.annotation.Ignore;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class BaseListOperation {
    @Parameter
    @Optional
    Filter filter;

    @Parameter
    @Optional
    OrderBy orderBy;

    @Ignore
    public Filter getFilter() {
        return filter;
    }

    @Ignore
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Ignore
    public OrderBy getOrderBy() {
        return orderBy;
    }

    @Ignore
    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    @Ignore
    protected void resolve(Client.Q q) {

        if (getOrderBy() != null){
            q.orderBy(getOrderBy().getProperties().toArray(new String[]{}));
        }

        if (getFilter() != null) {
            q.filter(getFilter().toRQL());
        }
    }
}
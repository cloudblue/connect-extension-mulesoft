/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.internal.clients.rql.Rql;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.List;

public class ListFilter implements Filter {
    private static final long serialVersionUID = 832613678750477025L;

    public enum Type {
        AND, OR
    }

    @Parameter
    private Type type;

    @Parameter
    private List<Filter> filters;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> monoFilters) {
        this.filters = monoFilters;
    }

    @Override
    public Rql toRQL() {
        Rql[] rs = getFilters().stream().map(Filter::toRQL).toArray(Rql[]::new);

        if (getType() == ListFilter.Type.AND) {
            return Rql.and(rs);
        } else if (getType() == ListFilter.Type.OR) {
            return Rql.or(rs);
        }

        return null;
    }
}

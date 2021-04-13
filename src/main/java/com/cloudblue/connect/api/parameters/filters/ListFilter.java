package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.util.List;

public class ListFilter extends Filter {
    public enum Type {
        and, or
    }

    @Parameter
    private Type type;

    @Parameter
    private List<Filter> filters;

    public ListFilter() {}

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
    public R toRQL() {
        R[] rs = getFilters().stream().map(Filter::toRQL).toArray(R[]::new);

        if (getType() == ListFilter.Type.and) {
            return R.and(rs);
        } else if (getType() == ListFilter.Type.or) {
            return R.or(rs);
        }

        return null;
    }
}

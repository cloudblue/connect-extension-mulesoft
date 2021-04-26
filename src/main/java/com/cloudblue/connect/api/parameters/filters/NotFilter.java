package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NotFilter implements Filter {

    @Parameter
    private Filter filter;

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public R toRQL() {
        return R.not(filter.toRQL());
    }
}

package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

public class InFilter extends MultiValueFilter {

    @Override
    public R toRQL() {
        return R.in(this.getProperty(), this.getValues().toArray());
    }
}

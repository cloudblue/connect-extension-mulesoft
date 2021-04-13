package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

public class OutFilter extends MultiValueFilter {
    @Override
    public R toRQL() {
        return R.out(this.getProperty(), this.getValues().toArray());
    }
}

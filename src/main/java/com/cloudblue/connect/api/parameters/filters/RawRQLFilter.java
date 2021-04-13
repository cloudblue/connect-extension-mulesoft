package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class RawRQLFilter extends Filter {
    @Parameter
    private String rawRql;

    public String getRawRql() {
        return rawRql;
    }

    public void setRawRql(String rawRql) {
        this.rawRql = rawRql;
    }

    @Override
    public R toRQL() {
        return R.expr(rawRql);
    }
}

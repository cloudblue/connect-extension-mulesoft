package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

public abstract class Filter {

    public abstract R toRQL();
}

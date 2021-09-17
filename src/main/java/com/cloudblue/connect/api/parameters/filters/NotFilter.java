/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.internal.clients.rql.R;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class NotFilter implements Filter {

    private static final long serialVersionUID = -4492215686446630841L;

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

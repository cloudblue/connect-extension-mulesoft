/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.api.clients.rql.R;

public class OutFilter extends MultiValueFilter {
    @Override
    public R toRQL() {
        return R.out(this.getProperty(), this.getValues().toArray());
    }
}

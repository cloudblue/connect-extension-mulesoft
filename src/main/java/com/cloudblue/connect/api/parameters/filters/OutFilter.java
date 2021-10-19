/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.internal.clients.rql.Rql;

import java.io.Serializable;

public class OutFilter extends MultiValueFilter implements Serializable {
    private static final long serialVersionUID = 8079200967712763339L;

    @Override
    public Rql toRQL() {
        return Rql.out(this.getProperty(), this.getValues().toArray());
    }
}

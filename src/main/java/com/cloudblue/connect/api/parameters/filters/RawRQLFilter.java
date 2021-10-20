/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.api.parameters.filters;

import com.cloudblue.connect.internal.clients.rql.Rql;
import org.mule.runtime.extension.api.annotation.param.Parameter;

import java.io.Serializable;

public class RawRQLFilter implements Filter, Serializable {
    private static final long serialVersionUID = -6000246210909653283L;
    
    @Parameter
    private String rawRql;

    public String getRawRql() {
        return rawRql;
    }

    public void setRawRql(String rawRql) {
        this.rawRql = rawRql;
    }

    @Override
    public Rql toRQL() {
        return Rql.expr(rawRql);
    }
}

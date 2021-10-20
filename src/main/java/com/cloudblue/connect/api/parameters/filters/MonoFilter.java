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

public class MonoFilter implements Filter, Serializable {

    private static final long serialVersionUID = 7059011858681517170L;

    public enum Type {
        EQ, NE, LT, LE, GT, GE, LIKE, ILIKE
    }

    @Parameter
    private Type type;

    @Parameter
    private String property;

    @Parameter
    private String value;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public Rql toRQL() {
        if (this.getType() == Type.EQ)
            return Rql.eq(this.getProperty(), this.getValue());
        else if (this.getType() == Type.NE)
            return Rql.ne(this.getProperty(), this.getValue());
        else if (this.getType() == Type.GE)
            return Rql.ge(this.getProperty(), this.getValue());
        else if (this.getType() == Type.GT)
            return Rql.gt(this.getProperty(), this.getValue());
        else if (this.getType() == Type.LE)
            return Rql.le(this.getProperty(), this.getValue());
        else if (this.getType() == Type.LT)
            return Rql.lt(this.getProperty(), this.getValue());
        else if (this.getType() == Type.LIKE)
            return Rql.like(this.getProperty(), this.getValue());
        else return Rql.ilike(this.getProperty(), this.getValue());
    }

}

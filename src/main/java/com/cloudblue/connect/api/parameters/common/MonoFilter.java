package com.cloudblue.connect.api.parameters.common;

import com.cloudblue.connect.api.clients.rql.R;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class MonoFilter extends Filter {
    
    public enum Type {
        eq, ne, lt, le, gt, ge, like, ilike
    }

    @Parameter
    private Type type;

    @Parameter
    private String property;

    @Parameter
    private String value;

    public MonoFilter() {}

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
    public R toRQL() {
        if (this.getType() == Type.eq)
            return R.eq(this.getProperty(), this.getValue());
        else if (this.getType() == Type.ne)
            return R.ne(this.getProperty(), this.getValue());
        else if (this.getType() == Type.ge)
            return R.ge(this.getProperty(), this.getValue());
        else if (this.getType() == Type.gt)
            return R.gt(this.getProperty(), this.getValue());
        else if (this.getType() == Type.le)
            return R.le(this.getProperty(), this.getValue());
        else if (this.getType() == Type.lt)
            return R.lt(this.getProperty(), this.getValue());
        else if (this.getType() == Type.like)
            return R.like(this.getProperty(), this.getValue());
        else return R.ilike(this.getProperty(), this.getValue());
    }

}

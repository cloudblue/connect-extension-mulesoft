package com.cloudblue.connect.api.rql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class R {
    public static final String NULL = "null()";
    public static final String EMPTY = "empty()";


    private static final String OPEN_BRACES = "(";
    private static final String CLOSE_BRACES = ")";
    private static final String COMA = ",";


    private enum Type {
        and, or, not, expr
    }

    private enum ExprType {
        eq, ne, lt, le, gt, ge, like, ilike, in, out
    }

    private Type type;
    private List<R> children;

    private ExprType exprType;
    private String property;
    private Object[] values;


    private static R expr(ExprType exprType, String property) {
        R r = new R();
        r.type = Type.expr;
        r.exprType = exprType;
        r.property = property;

        return r;
    }


    private static R comp(ExprType exprType, String property, Object... values) {
        R r = expr(exprType, property);
        r.values = values;

        return r;
    }


    public static R eq(String property, Object value) {
        return comp(ExprType.eq, property, value);
    }

    public static R ne(String property, Object value) {
        return comp(ExprType.ne, property, value);
    }

    public static R lt(String property, Object value) {
        return comp(ExprType.lt, property, value);
    }

    public static R le(String property, Object value) {
        return comp(ExprType.le, property, value);
    }

    public static R gt(String property, Object value) {
        return comp(ExprType.gt, property, value);
    }

    public static R ge(String property, Object value) {
        return comp(ExprType.ge, property, value);
    }

    public static R like(String property, String value) {
        return comp(ExprType.like, property, value);
    }

    public static R ilike(String property, String value) {
        return comp(ExprType.ilike, property, value);
    }

    public static R in(String property, Object... values) {
        return comp(ExprType.in, property, values);
    }

    public static R out(String property, Object... values) {
        return comp(ExprType.out, property, values);
    }

    private static R multiValuedLogical(Type type, R first, R... remaining) {
        R r = new R();
        r.type = type;

        r.children = new ArrayList<>();
        r.children.add(first);
        r.children.addAll(Arrays.asList(remaining));

        return r;
    }

    public static R and(R first, R... remaining) {
        return multiValuedLogical(Type.and, first, remaining);
    }

    public static R or(R first, R... remaining) {
        return multiValuedLogical(Type.or, first, remaining);
    }

    public static R not(R only) {
        R r = new R();
        r.type = Type.not;

        r.children = new ArrayList<>();
        r.children.add(only);

        return r;
    }

    private Object[] preprocessValues() {
        return Arrays.stream(this.values).map(s -> {
            if (s == null) return NULL;
            return s;
        }).toArray();
    }

    public String toString() {
        StringBuilder rql = new StringBuilder();

        if (this.type == Type.expr) {
            rql.append(this.exprType.toString());
            rql.append(OPEN_BRACES);
            rql.append(this.property);
            rql.append(COMA);

            Object[] processedValues = this.preprocessValues();

            if (processedValues.length == 1) {
                rql.append(processedValues[0]);
            } else {
                rql.append(OPEN_BRACES);
                rql.append(
                        Arrays.stream(processedValues)
                        .map(String::valueOf).collect(Collectors.joining(COMA))
                );
                rql.append(CLOSE_BRACES);
            }

        } else {
            rql.append(this.type.toString());
            rql.append(OPEN_BRACES);
            rql.append(
                    this.children.stream().map(R::toString)
                            .collect(Collectors.joining(COMA))
            );
        }
        rql.append(CLOSE_BRACES);

        return rql.toString();
    }

}

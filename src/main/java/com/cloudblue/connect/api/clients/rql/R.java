package com.cloudblue.connect.api.clients.rql;

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
        and, or, not, literal, expr
    }

    private enum LiteralType {
        eq, ne, lt, le, gt, ge, like, ilike, in, out, ordering
    }

    private Type type;
    private List<R> children;

    private LiteralType literalType;
    private String property;
    private Object[] values;


    private static R expr(LiteralType literalType, String property) {
        R r = new R();
        r.type = Type.literal;
        r.literalType = literalType;
        r.property = property;

        return r;
    }


    private static R comp(LiteralType literalType, String property, Object... values) {
        R r = expr(literalType, property);
        r.values = values;

        return r;
    }


    public static R eq(String property, Object value) {
        return comp(LiteralType.eq, property, value);
    }

    public static R ne(String property, Object value) {
        return comp(LiteralType.ne, property, value);
    }

    public static R lt(String property, Object value) {
        return comp(LiteralType.lt, property, value);
    }

    public static R le(String property, Object value) {
        return comp(LiteralType.le, property, value);
    }

    public static R gt(String property, Object value) {
        return comp(LiteralType.gt, property, value);
    }

    public static R ge(String property, Object value) {
        return comp(LiteralType.ge, property, value);
    }

    public static R like(String property, String value) {
        return comp(LiteralType.like, property, value);
    }

    public static R ilike(String property, String value) {
        return comp(LiteralType.ilike, property, value);
    }

    public static R in(String property, Object... values) {
        return comp(LiteralType.in, property, values);
    }

    public static R out(String property, Object... values) {
        return comp(LiteralType.out, property, values);
    }

    public static R orderBy(String... values) {
        return comp(LiteralType.ordering, null, values);
    }

    private static R multiValuedLogical(Type type, R... remaining) {
        R r = new R();
        r.type = type;

        r.children = new ArrayList<>();
        r.children.addAll(Arrays.asList(remaining));

        return r;
    }

    public static R and(R... rs) {
        return multiValuedLogical(Type.and, rs);
    }

    public static R or(R... rs) {
        return multiValuedLogical(Type.or, rs);
    }

    public static R not(R only) {
        R r = new R();
        r.type = Type.not;

        r.children = new ArrayList<>();
        r.children.add(only);

        return r;
    }

    public static R expr(String expr) {
        R r = new R();
        r.type = Type.expr;
        r.property = expr;

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

        if (this.type == Type.literal) {
            Object[] processedValues = this.preprocessValues();

            rql.append(this.literalType.toString());
            rql.append(OPEN_BRACES);

            if (this.literalType == LiteralType.ordering) {
                rql.append(
                        Arrays.stream(processedValues)
                                .map(String::valueOf).collect(Collectors.joining(COMA))
                );
            } else {
                rql.append(this.property);
                rql.append(COMA);



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
            }
            rql.append(CLOSE_BRACES);

        } else if (this.type == Type.expr) {
            rql.append(property);
        } else {
            rql.append(this.type.toString());
            rql.append(OPEN_BRACES);
            rql.append(
                    this.children.stream().map(R::toString)
                            .collect(Collectors.joining(COMA))
            );
            rql.append(CLOSE_BRACES);
        }

        return rql.toString();
    }

}

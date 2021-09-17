package com.cloudblue.connect.internal.clients.rql;

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
        AND, OR, NOT, LITERAL, EXPR
    }

    private enum LiteralType {
        EQ, NE, LT, LE, GT, GE, LIKE, ILIKE, IN, OUT, ORDERING
    }

    private Type type;
    private List<R> children;

    private LiteralType literalType;
    private String property;
    private Object[] values;

    private static R expr(LiteralType literalType, String property) {
        R r = new R();
        r.type = Type.LITERAL;
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
        return comp(LiteralType.EQ, property, value);
    }

    public static R ne(String property, Object value) {
        return comp(LiteralType.NE, property, value);
    }

    public static R lt(String property, Object value) {
        return comp(LiteralType.LT, property, value);
    }

    public static R le(String property, Object value) {
        return comp(LiteralType.LE, property, value);
    }

    public static R gt(String property, Object value) {
        return comp(LiteralType.GT, property, value);
    }

    public static R ge(String property, Object value) {
        return comp(LiteralType.GE, property, value);
    }

    public static R like(String property, String value) {
        return comp(LiteralType.LIKE, property, value);
    }

    public static R ilike(String property, String value) {
        return comp(LiteralType.ILIKE, property, value);
    }

    public static R in(String property, Object... values) {
        return comp(LiteralType.IN, property, values);
    }

    public static R out(String property, Object... values) {
        return comp(LiteralType.OUT, property, values);
    }

    public static R orderBy(String... values) {
        return comp(LiteralType.ORDERING, null, (Object[]) values);
    }

    private static R multiValuedLogical(Type type, R... remaining) {
        R r = new R();
        r.type = type;

        r.children = new ArrayList<>();
        r.children.addAll(Arrays.asList(remaining));

        return r;
    }

    public static R and(R... rs) {
        return multiValuedLogical(Type.AND, rs);
    }

    public static R or(R... rs) {
        return multiValuedLogical(Type.OR, rs);
    }

    public static R not(R only) {
        R r = new R();
        r.type = Type.NOT;

        r.children = new ArrayList<>();
        r.children.add(only);

        return r;
    }

    public static R expr(String expr) {
        R r = new R();
        r.type = Type.EXPR;
        r.property = expr;

        return r;
    }

    private Object[] preprocessValues() {
        return Arrays.stream(this.values).map(s -> {
            if (s == null) return NULL;
            return s;
        }).toArray();
    }

    private void toStringForLiteral(StringBuilder rql) {
        Object[] processedValues = this.preprocessValues();

        rql.append(this.literalType.toString().toLowerCase());
        rql.append(OPEN_BRACES);

        if (this.literalType == LiteralType.ORDERING) {
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

    }

    private void toStringForLogical(StringBuilder rql) {
        String prefix = this.type.toString().toLowerCase();
        String separator = COMA;

        if (type == Type.AND || type == Type.OR)
            prefix = "";

        if (type == Type.AND)
            separator = "&";

        if (type == Type.OR)
            separator = "|";

        rql.append(prefix);
        rql.append(OPEN_BRACES);
        rql.append(
                this.children.stream().map(R::toString)
                        .collect(Collectors.joining(separator))
        );
        rql.append(CLOSE_BRACES);
    }

    public String toString() {
        StringBuilder rql = new StringBuilder();

        if (this.type == Type.LITERAL) {
            toStringForLiteral(rql);
        } else if (this.type == Type.EXPR) {
            rql.append(property);
        } else {
            toStringForLogical(rql);
        }

        return rql.toString();
    }

}

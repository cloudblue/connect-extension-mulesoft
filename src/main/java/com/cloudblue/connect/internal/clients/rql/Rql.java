/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.clients.rql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rql {
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
    private List<Rql> children;

    private LiteralType literalType;
    private String property;
    private Object[] values;

    private static Rql expr(LiteralType literalType, String property) {
        Rql r = new Rql();
        r.type = Type.LITERAL;
        r.literalType = literalType;
        r.property = property;

        return r;
    }

    private static Rql comp(LiteralType literalType, String property, Object... values) {
        Rql r = expr(literalType, property);
        r.values = values;

        return r;
    }

    public static Rql eq(String property, Object value) {
        return comp(LiteralType.EQ, property, value);
    }

    public static Rql ne(String property, Object value) {
        return comp(LiteralType.NE, property, value);
    }

    public static Rql lt(String property, Object value) {
        return comp(LiteralType.LT, property, value);
    }

    public static Rql le(String property, Object value) {
        return comp(LiteralType.LE, property, value);
    }

    public static Rql gt(String property, Object value) {
        return comp(LiteralType.GT, property, value);
    }

    public static Rql ge(String property, Object value) {
        return comp(LiteralType.GE, property, value);
    }

    public static Rql like(String property, String value) {
        return comp(LiteralType.LIKE, property, value);
    }

    public static Rql ilike(String property, String value) {
        return comp(LiteralType.ILIKE, property, value);
    }

    public static Rql in(String property, Object... values) {
        return comp(LiteralType.IN, property, values);
    }

    public static Rql out(String property, Object... values) {
        return comp(LiteralType.OUT, property, values);
    }

    public static Rql orderBy(String... values) {
        return comp(LiteralType.ORDERING, null, (Object[]) values);
    }

    private static Rql multiValuedLogical(Type type, Rql... remaining) {
        Rql r = new Rql();
        r.type = type;

        r.children = new ArrayList<>();
        r.children.addAll(Arrays.asList(remaining));

        return r;
    }

    public static Rql and(Rql... rs) {
        return multiValuedLogical(Type.AND, rs);
    }

    public static Rql or(Rql... rs) {
        return multiValuedLogical(Type.OR, rs);
    }

    public static Rql not(Rql only) {
        Rql r = new Rql();
        r.type = Type.NOT;

        r.children = new ArrayList<>();
        r.children.add(only);

        return r;
    }

    public static Rql expr(String expr) {
        Rql r = new Rql();
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
                this.children.stream().map(Rql::toString)
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

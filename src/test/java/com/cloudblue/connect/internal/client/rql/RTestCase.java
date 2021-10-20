/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.client.rql;

import com.cloudblue.connect.internal.clients.rql.Rql;
import org.junit.Assert;
import org.junit.Test;

import static com.cloudblue.connect.internal.clients.rql.Rql.EMPTY;
import static com.cloudblue.connect.internal.clients.rql.Rql.and;
import static com.cloudblue.connect.internal.clients.rql.Rql.eq;
import static com.cloudblue.connect.internal.clients.rql.Rql.expr;
import static com.cloudblue.connect.internal.clients.rql.Rql.ge;
import static com.cloudblue.connect.internal.clients.rql.Rql.gt;
import static com.cloudblue.connect.internal.clients.rql.Rql.ilike;
import static com.cloudblue.connect.internal.clients.rql.Rql.in;
import static com.cloudblue.connect.internal.clients.rql.Rql.le;
import static com.cloudblue.connect.internal.clients.rql.Rql.like;
import static com.cloudblue.connect.internal.clients.rql.Rql.lt;
import static com.cloudblue.connect.internal.clients.rql.Rql.ne;
import static com.cloudblue.connect.internal.clients.rql.Rql.not;
import static com.cloudblue.connect.internal.clients.rql.Rql.or;
import static com.cloudblue.connect.internal.clients.rql.Rql.orderBy;
import static com.cloudblue.connect.internal.clients.rql.Rql.out;


public class RTestCase {
    @Test
    public void testEqStr() {
        Rql r = eq("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("eq(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void testEqInt() {
        Rql r = eq("count", 7);

        Assert.assertEquals("eq(count,7)", r.toString());
    }

    @Test
    public void testEqDouble() {
        Rql r = eq("price", 34.56);

        Assert.assertEquals("eq(price,34.56)", r.toString());
    }

    @Test
    public void testEqNull() {
        Rql r = eq("asset.id", null);

        Assert.assertEquals("eq(asset.id,null())", r.toString());
    }

    @Test
    public void testEqEmpty() {
        Rql r = eq("asset.tiers", EMPTY);

        Assert.assertEquals("eq(asset.tiers,empty())", r.toString());
    }

    @Test
    public void testNeStr(){
        Rql r = ne("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("ne(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void testNeNull(){
        Rql r = ne("asset.id", null);

        Assert.assertEquals("ne(asset.id,null())", r.toString());
    }

    @Test
    public void testGtStr(){
        Rql r = gt("created", "02-12-2020");

        Assert.assertEquals("gt(created,02-12-2020)", r.toString());
    }

    @Test
    public void testGtInt(){
        Rql r = gt("count", 5);

        Assert.assertEquals("gt(count,5)", r.toString());
    }

    @Test
    public void testGtDouble(){
        Rql r = gt("price", 7.45);

        Assert.assertEquals("gt(price,7.45)", r.toString());
    }

    @Test
    public void testGeStr(){
        Rql r = ge("created", "02-12-2020");

        Assert.assertEquals("ge(created,02-12-2020)", r.toString());
    }

    @Test
    public void testGeInt(){
        Rql r = ge("count", 5);

        Assert.assertEquals("ge(count,5)", r.toString());
    }

    @Test
    public void testGeDouble(){
        Rql r = ge("price", 7.45);

        Assert.assertEquals("ge(price,7.45)", r.toString());
    }

    @Test
    public void testLtStr(){
        Rql r = lt("created", "02-12-2020");

        Assert.assertEquals("lt(created,02-12-2020)", r.toString());
    }

    @Test
    public void testLtInt(){
        Rql r = lt("count", 5);

        Assert.assertEquals("lt(count,5)", r.toString());
    }

    @Test
    public void testLtDouble(){
        Rql r = lt("price", 7.45);

        Assert.assertEquals("lt(price,7.45)", r.toString());
    }

    @Test
    public void testLeStr(){
        Rql r = le("created", "02-12-2020");

        Assert.assertEquals("le(created,02-12-2020)", r.toString());
    }

    @Test
    public void testLeInt(){
        Rql r = le("count", 5);

        Assert.assertEquals("le(count,5)", r.toString());
    }

    @Test
    public void testLeDouble(){
        Rql r = le("price", 7.45);

        Assert.assertEquals("le(price,7.45)", r.toString());
    }

    @Test
    public void testLike(){
        Rql r = like("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("like(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void testIlike(){
        Rql r = ilike("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("ilike(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void testIn(){
        Rql r = in("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("in(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void testOut(){
        Rql r = out("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("out(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void testAnd() {
        Rql r = and(
                eq("count", 7),
                ne("asset.id", null),
                le("price", 7.45)
        );

        Assert.assertEquals(
                "(eq(count,7)&ne(asset.id,null())&le(price,7.45))",
                r.toString()
        );
    }

    @Test
    public void testOr() {
        Rql r = or(
                eq("count", 7),
                ne("asset.id", null),
                le("price", 7.45)
        );

        Assert.assertEquals(
                "(eq(count,7)|ne(asset.id,null())|le(price,7.45))",
                r.toString()
        );
    }

    @Test
    public void testNot() {
        Rql r = not(eq("count", 7));

        Assert.assertEquals(
                "not(eq(count,7))",
                r.toString()
        );
    }

    @Test
    public void testExpr() {
        Rql r = not(expr("eq(count,20)"));

        Assert.assertEquals(
                "not(eq(count,20))",
                r.toString()
        );
    }

    @Test
    public void testOrderBy() {
        Rql r = orderBy("-id");

        Assert.assertEquals(
                "ordering(-id)",
                r.toString()
        );
    }

    @Test
    public void testComplexRql() {
        Rql r = and(
                not(eq("count", 7)),
                ne("asset.id", null),
                and(
                        le("price", 7.45),
                        ge("price", 5.45)
                ),
                or(
                        eq("count", 7),
                        eq("count", 11)
                )
        );

        Assert.assertEquals(
                "(" +
                        "not(eq(count,7))&" +
                        "ne(asset.id,null())&" +
                        "(le(price,7.45)&ge(price,5.45))&" +
                        "(eq(count,7)|eq(count,11))" +
                        ")",
                r.toString()
        );
    }
}

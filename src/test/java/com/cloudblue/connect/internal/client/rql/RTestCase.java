/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.client.rql;

import com.cloudblue.connect.internal.clients.rql.R;

import org.junit.Assert;
import org.junit.Test;

import static com.cloudblue.connect.internal.clients.rql.R.*;


public class RTestCase {
    @Test
    public void testEqStr() {
        R r = eq("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("eq(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void testEqInt() {
        R r = eq("count", 7);

        Assert.assertEquals("eq(count,7)", r.toString());
    }

    @Test
    public void testEqDouble() {
        R r = eq("price", 34.56);

        Assert.assertEquals("eq(price,34.56)", r.toString());
    }

    @Test
    public void testEqNull() {
        R r = eq("asset.id", null);

        Assert.assertEquals("eq(asset.id,null())", r.toString());
    }

    @Test
    public void testEqEmpty() {
        R r = eq("asset.tiers", EMPTY);

        Assert.assertEquals("eq(asset.tiers,empty())", r.toString());
    }

    @Test
    public void testNeStr(){
        R r = ne("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("ne(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void testNeNull(){
        R r = ne("asset.id", null);

        Assert.assertEquals("ne(asset.id,null())", r.toString());
    }

    @Test
    public void testGtStr(){
        R r = gt("created", "02-12-2020");

        Assert.assertEquals("gt(created,02-12-2020)", r.toString());
    }

    @Test
    public void testGtInt(){
        R r = gt("count", 5);

        Assert.assertEquals("gt(count,5)", r.toString());
    }

    @Test
    public void testGtDouble(){
        R r = gt("price", 7.45);

        Assert.assertEquals("gt(price,7.45)", r.toString());
    }

    @Test
    public void testGeStr(){
        R r = ge("created", "02-12-2020");

        Assert.assertEquals("ge(created,02-12-2020)", r.toString());
    }

    @Test
    public void testGeInt(){
        R r = ge("count", 5);

        Assert.assertEquals("ge(count,5)", r.toString());
    }

    @Test
    public void testGeDouble(){
        R r = ge("price", 7.45);

        Assert.assertEquals("ge(price,7.45)", r.toString());
    }

    @Test
    public void testLtStr(){
        R r = lt("created", "02-12-2020");

        Assert.assertEquals("lt(created,02-12-2020)", r.toString());
    }

    @Test
    public void testLtInt(){
        R r = lt("count", 5);

        Assert.assertEquals("lt(count,5)", r.toString());
    }

    @Test
    public void testLtDouble(){
        R r = lt("price", 7.45);

        Assert.assertEquals("lt(price,7.45)", r.toString());
    }

    @Test
    public void testLeStr(){
        R r = le("created", "02-12-2020");

        Assert.assertEquals("le(created,02-12-2020)", r.toString());
    }

    @Test
    public void testLeInt(){
        R r = le("count", 5);

        Assert.assertEquals("le(count,5)", r.toString());
    }

    @Test
    public void testLeDouble(){
        R r = le("price", 7.45);

        Assert.assertEquals("le(price,7.45)", r.toString());
    }

    @Test
    public void testLike(){
        R r = like("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("like(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void testIlike(){
        R r = ilike("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("ilike(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void testIn(){
        R r = in("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("in(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void testOut(){
        R r = out("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("out(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void testAnd() {
        R r = and(
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
        R r = or(
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
        R r = not(eq("count", 7));

        Assert.assertEquals(
                "not(eq(count,7))",
                r.toString()
        );
    }

    @Test
    public void testExpr() {
        R r = not(expr("eq(count,20)"));

        Assert.assertEquals(
                "not(eq(count,20))",
                r.toString()
        );
    }

    @Test
    public void testOrderBy() {
        R r = orderBy("-id");

        Assert.assertEquals(
                "ordering(-id)",
                r.toString()
        );
    }

    @Test
    public void testComplexRql() {
        R r = and(
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

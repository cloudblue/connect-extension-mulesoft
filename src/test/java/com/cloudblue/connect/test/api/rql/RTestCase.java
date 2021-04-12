package com.cloudblue.connect.test.api.rql;

import com.cloudblue.connect.api.clients.rql.R;
import static com.cloudblue.connect.api.clients.rql.R.*;

import org.junit.Assert;
import org.junit.Test;

public class RTestCase {
    @Test
    public void test_eq_str() {
        R r = eq("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("eq(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void test_eq_int() {
        R r = eq("count", 7);

        Assert.assertEquals("eq(count,7)", r.toString());
    }

    @Test
    public void test_eq_double() {
        R r = eq("price", 34.56);

        Assert.assertEquals("eq(price,34.56)", r.toString());
    }

    @Test
    public void test_eq_null() {
        R r = eq("asset.id", null);

        Assert.assertEquals("eq(asset.id,null())", r.toString());
    }

    @Test
    public void test_eq_empty() {
        R r = eq("asset.tiers", EMPTY);

        Assert.assertEquals("eq(asset.tiers,empty())", r.toString());
    }

    @Test
    public void test_ne_str(){
        R r = ne("asset.id", "AS-0000-0000-0000");

        Assert.assertEquals("ne(asset.id,AS-0000-0000-0000)", r.toString());
    }

    @Test
    public void test_ne_null(){
        R r = ne("asset.id", null);

        Assert.assertEquals("ne(asset.id,null())", r.toString());
    }

    @Test
    public void test_gt_str(){
        R r = gt("created", "02-12-2020");

        Assert.assertEquals("gt(created,02-12-2020)", r.toString());
    }

    @Test
    public void test_gt_int(){
        R r = gt("count", 5);

        Assert.assertEquals("gt(count,5)", r.toString());
    }

    @Test
    public void test_gt_double(){
        R r = gt("price", 7.45);

        Assert.assertEquals("gt(price,7.45)", r.toString());
    }

    @Test
    public void test_ge_str(){
        R r = ge("created", "02-12-2020");

        Assert.assertEquals("ge(created,02-12-2020)", r.toString());
    }

    @Test
    public void test_ge_int(){
        R r = ge("count", 5);

        Assert.assertEquals("ge(count,5)", r.toString());
    }

    @Test
    public void test_ge_double(){
        R r = ge("price", 7.45);

        Assert.assertEquals("ge(price,7.45)", r.toString());
    }

    @Test
    public void test_lt_str(){
        R r = lt("created", "02-12-2020");

        Assert.assertEquals("lt(created,02-12-2020)", r.toString());
    }

    @Test
    public void test_lt_int(){
        R r = lt("count", 5);

        Assert.assertEquals("lt(count,5)", r.toString());
    }

    @Test
    public void test_lt_double(){
        R r = lt("price", 7.45);

        Assert.assertEquals("lt(price,7.45)", r.toString());
    }

    @Test
    public void test_le_str(){
        R r = le("created", "02-12-2020");

        Assert.assertEquals("le(created,02-12-2020)", r.toString());
    }

    @Test
    public void test_le_int(){
        R r = le("count", 5);

        Assert.assertEquals("le(count,5)", r.toString());
    }

    @Test
    public void test_le_double(){
        R r = le("price", 7.45);

        Assert.assertEquals("le(price,7.45)", r.toString());
    }

    @Test
    public void test_like(){
        R r = like("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("like(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void test_ilike(){
        R r = ilike("asset.id", "AS-0000-*-0000");

        Assert.assertEquals("ilike(asset.id,AS-0000-*-0000)", r.toString());
    }

    @Test
    public void test_in(){
        R r = in("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("in(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void test_out(){
        R r = out("asset.id", "AS-0000-0000-0000", 45, 56.23);

        Assert.assertEquals("out(asset.id,(AS-0000-0000-0000,45,56.23))", r.toString());
    }

    @Test
    public void test_and() {
        R r = and(
                eq("count", 7),
                ne("asset.id", null),
                le("price", 7.45)
        );

        Assert.assertEquals(
                "and(eq(count,7),ne(asset.id,null()),le(price,7.45))",
                r.toString()
        );
    }

    @Test
    public void test_or() {
        R r = or(
                eq("count", 7),
                ne("asset.id", null),
                le("price", 7.45)
        );

        Assert.assertEquals(
                "or(eq(count,7),ne(asset.id,null()),le(price,7.45))",
                r.toString()
        );
    }

    @Test
    public void test_not() {
        R r = not(eq("count", 7));

        Assert.assertEquals(
                "not(eq(count,7))",
                r.toString()
        );
    }

    @Test
    public void test_complex_rql() {
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
                "and(" +
                        "not(eq(count,7))," +
                        "ne(asset.id,null())," +
                        "and(le(price,7.45),ge(price,5.45))," +
                        "or(eq(count,7),eq(count,11))" +
                        ")",
                r.toString()
        );
    }
}

/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.api.client;

import com.cloudblue.connect.api.parameters.filters.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FilterTestCase {
    @Test
    public void test_mono_filter() {
        MonoFilter filter = new MonoFilter();
        filter.setType(MonoFilter.Type.EQ);
        filter.setProperty("asset.id");
        filter.setValue("AS-0000-0000");

        Assert.assertEquals(
                "eq(asset.id,AS-0000-0000)",
                filter.toRQL().toString()
        );
    }

    @Test
    public void test_list_filter_simple() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.EQ);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.NE);
        m2.setProperty("status");
        m2.setValue("approved");

        ListFilter listFilter = new ListFilter();
        listFilter.setType(ListFilter.Type.OR);
        listFilter.setFilters(new ArrayList<>());
        listFilter.getFilters().add(m1);
        listFilter.getFilters().add(m2);

        Assert.assertEquals(
                "(" +
                        "eq(asset.id,AS-0000-0000)|" +
                        "ne(status,approved)" +
                        ")",
                listFilter.toRQL().toString()
        );
    }

    @Test
    public void test_list_filter_complex() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.EQ);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.NE);
        m2.setProperty("status");
        m2.setValue("approved");

        ListFilter l1 = new ListFilter();
        l1.setType(ListFilter.Type.OR);
        l1.setFilters(new ArrayList<>());
        l1.getFilters().add(m1);
        l1.getFilters().add(m2);

        ListFilter l2 = new ListFilter();
        l2.setType(ListFilter.Type.AND);
        l2.setFilters(new ArrayList<>());
        l2.getFilters().add(m1);
        l2.getFilters().add(m2);

        ListFilter l3 = new ListFilter();
        l3.setType(ListFilter.Type.AND);
        l3.setFilters(new ArrayList<>());
        l3.getFilters().add(l1);
        l3.getFilters().add(l2);
        l3.getFilters().add(m1);

        Assert.assertEquals(
                "(" +
                        "(eq(asset.id,AS-0000-0000)|ne(status,approved))&" +
                        "(eq(asset.id,AS-0000-0000)&ne(status,approved))&" +
                        "eq(asset.id,AS-0000-0000)" +
                        ")",
                l3.toRQL().toString()
        );
    }

    @Test
    public void test_not_filter_simple() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.EQ);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        NotFilter n1 = new NotFilter();
        n1.setFilter(m1);

        Assert.assertEquals(
                "not(eq(asset.id,AS-0000-0000))",
                n1.toRQL().toString()
        );

    }

    @Test
    public void test_not_filter_complex() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.EQ);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.NE);
        m2.setProperty("status");
        m2.setValue("approved");

        ListFilter l1 = new ListFilter();
        l1.setType(ListFilter.Type.OR);
        l1.setFilters(new ArrayList<>());
        l1.getFilters().add(m1);
        l1.getFilters().add(m2);

        ListFilter l2 = new ListFilter();
        l2.setType(ListFilter.Type.AND);
        l2.setFilters(new ArrayList<>());
        l2.getFilters().add(m1);
        l2.getFilters().add(m2);

        ListFilter l3 = new ListFilter();
        l3.setType(ListFilter.Type.AND);
        l3.setFilters(new ArrayList<>());
        l3.getFilters().add(l1);
        l3.getFilters().add(l2);
        l3.getFilters().add(m1);

        NotFilter n1 = new NotFilter();
        n1.setFilter(l3);

        Assert.assertEquals(
                "not(" +
                        "(" +
                        "(eq(asset.id,AS-0000-0000)|ne(status,approved))&" +
                        "(eq(asset.id,AS-0000-0000)&ne(status,approved))&" +
                        "eq(asset.id,AS-0000-0000)" +
                        ")" +
                        ")",
                n1.toRQL().toString()
        );

    }

    @Test
    public void test_in_filter() {
        InFilter inFilter = new InFilter();
        inFilter.setProperty("asset.id");
        inFilter.setValues(new ArrayList<>());
        inFilter.getValues().add("AS-001");
        inFilter.getValues().add("AS-002");
        inFilter.getValues().add("AS-003");

        Assert.assertEquals(
                "in(asset.id,(AS-001,AS-002,AS-003))",
                inFilter.toRQL().toString()
        );
    }

    @Test
    public void test_out_filter() {
        OutFilter outFilter = new OutFilter();
        outFilter.setProperty("asset.id");
        outFilter.setValues(new ArrayList<>());
        outFilter.getValues().add("AS-001");
        outFilter.getValues().add("AS-002");
        outFilter.getValues().add("AS-003");

        Assert.assertEquals(
                "out(asset.id,(AS-001,AS-002,AS-003))",
                outFilter.toRQL().toString()
        );
    }

    @Test
    public void test_raw_Rql_filter() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.EQ);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.NE);
        m2.setProperty("status");
        m2.setValue("approved");

        RawRQLFilter rqlFilter = new RawRQLFilter();
        rqlFilter.setRawRql("eq(property,value)");

        ListFilter listFilter = new ListFilter();
        listFilter.setType(ListFilter.Type.OR);
        listFilter.setFilters(new ArrayList<>());
        listFilter.getFilters().add(m1);
        listFilter.getFilters().add(m2);
        listFilter.getFilters().add(rqlFilter);

        Assert.assertEquals(
                "(" +
                        "eq(asset.id,AS-0000-0000)|" +
                        "ne(status,approved)|" +
                        "eq(property,value)" +
                        ")",
                listFilter.toRQL().toString()
        );
    }
}

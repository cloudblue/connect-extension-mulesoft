package com.cloudblue.connect.test.api.client;

import com.cloudblue.connect.api.parameters.common.ListFilter;
import com.cloudblue.connect.api.parameters.common.MonoFilter;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class FilterTestCase {
    @Test
    public void test_mono_filter() {
        MonoFilter filter = new MonoFilter();
        filter.setType(MonoFilter.Type.eq);
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
        m1.setType(MonoFilter.Type.eq);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.ne);
        m2.setProperty("status");
        m2.setValue("approved");

        ListFilter listFilter = new ListFilter();
        listFilter.setType(ListFilter.Type.or);
        listFilter.setFilters(new ArrayList<>());
        listFilter.getFilters().add(m1);
        listFilter.getFilters().add(m2);

        Assert.assertEquals(
                "or(" +
                        "eq(asset.id,AS-0000-0000)," +
                        "ne(status,approved)" +
                        ")",
                listFilter.toRQL().toString()
        );
    }

    @Test
    public void test_list_filter_complex() {
        MonoFilter m1 = new MonoFilter();
        m1.setType(MonoFilter.Type.eq);
        m1.setProperty("asset.id");
        m1.setValue("AS-0000-0000");

        MonoFilter m2 = new MonoFilter();
        m2.setType(MonoFilter.Type.ne);
        m2.setProperty("status");
        m2.setValue("approved");

        ListFilter l1 = new ListFilter();
        l1.setType(ListFilter.Type.or);
        l1.setFilters(new ArrayList<>());
        l1.getFilters().add(m1);
        l1.getFilters().add(m2);

        ListFilter l2 = new ListFilter();
        l2.setType(ListFilter.Type.and);
        l2.setFilters(new ArrayList<>());
        l2.getFilters().add(m1);
        l2.getFilters().add(m2);

        ListFilter l3 = new ListFilter();
        l3.setType(ListFilter.Type.and);
        l3.setFilters(new ArrayList<>());
        l3.getFilters().add(l1);
        l3.getFilters().add(l2);
        l3.getFilters().add(m1);

        Assert.assertEquals(
                "and(" +
                        "or(eq(asset.id,AS-0000-0000),ne(status,approved))," +
                        "and(eq(asset.id,AS-0000-0000),ne(status,approved))," +
                        "eq(asset.id,AS-0000-0000)" +
                        ")",
                l3.toRQL().toString()
        );
    }
}

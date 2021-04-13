package com.cloudblue.connect.api.parameters.utils;

import com.cloudblue.connect.api.clients.rql.R;
import com.cloudblue.connect.api.parameters.filters.Filter;
import com.cloudblue.connect.api.parameters.filters.ListFilter;
import com.cloudblue.connect.api.parameters.filters.MonoFilter;

public class Filters {
    public static R populateFilters(Filter filter) {
        if (filter instanceof MonoFilter) {
            MonoFilter monoFilter = (MonoFilter) filter;

            if (monoFilter.getType() == MonoFilter.Type.eq)
                return R.eq(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.ne)
                return R.ne(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.ge)
                return R.ge(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.gt)
                return R.gt(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.le)
                return R.le(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.lt)
                return R.lt(monoFilter.getProperty(), monoFilter.getValue());
            else if (monoFilter.getType() == MonoFilter.Type.like)
                return R.like(monoFilter.getProperty(), monoFilter.getValue());
            else return R.ilike(monoFilter.getProperty(), monoFilter.getValue());
        } else {
            ListFilter listFilter = (ListFilter) filter;

            R[] rs = listFilter.getFilters().stream().map(Filters::populateFilters).toArray(R[]::new);

            if (listFilter.getType() == ListFilter.Type.and) {
                return R.and(rs);
            } else if (listFilter.getType() == ListFilter.Type.or) {
                return R.or(rs);
            }
        }

        return null;
    }
}

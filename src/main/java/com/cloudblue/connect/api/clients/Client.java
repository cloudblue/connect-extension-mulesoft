package com.cloudblue.connect.api.clients;

import com.cloudblue.connect.api.clients.rql.R;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.clients.utils.RequestUtil;
import com.cloudblue.connect.api.exceptions.CBCException;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;

public class Client extends BaseClient {

    public static final class Q <T> {
        private final List<String> ns = new ArrayList<>();
        private final Map<Integer, String> nsKeyMap = new HashMap<>();
        private final Map<String, String> filters = new HashMap<>();
        private final List<R> rqlFilters = new ArrayList<>();
        private final List<String> orderBys = new ArrayList<>();
        private int limit = 100;

        private final TypeReference<T> type =new TypeReference<T>() {};

        private final Client client;

        public Q(Client client) {
            this.client = client;
        }

        private String getUrl() {
            StringBuilder builder = new StringBuilder();

            if (!ns.isEmpty()) {
                for (int position = 0; position < ns.size(); position++) {
                    builder.append("/");
                    builder.append(ns.get(position));

                    String resourceId = nsKeyMap.get(position);

                    if (resourceId != null) {
                        builder.append("/");
                        builder.append(resourceId);
                    }
                }
            }

            return builder.toString();
        }

        private String processFilters(String url) {
            String filterStr = RequestUtil.buildQueryParameters(filters);

            rqlFilters.add(R.orderBy(orderBys.toArray(new String[]{})));
            String rqlStr = R.and(rqlFilters.toArray(new R[]{})).toString();

            String limitStr = "limit=" + limit;

            if (url.contains("?")) url = url.concat("&");
            else url = url.concat("?");

            return url.concat(rqlStr).concat("&").concat(filterStr).concat("&").concat(limitStr);
        }

        private String getFinalUrl(String action) {
            if (action != null && !action.isEmpty())
                return processFilters(getUrl() + "/" + action);
            else
                return processFilters(getUrl());
        }

        public Q<T> ns(String ns) {
            return collection(ns, null);
        }

        public Q<T> collection(String ns, String key) {
            this.ns.add(ns);
            this.nsKeyMap.put(this.ns.size() - 1, key);

            return this;
        }

        public Q<T> filter(String property, String value) {
            this.filters.put(property, value);

            return this;
        }

        public Q<T> filter(R... r) {
            this.rqlFilters.addAll(Arrays.asList(r));

            return this;
        }

        public Q<T> orderBy(String... orderBys) {
            this.orderBys.addAll(Arrays.asList(orderBys));

            return this;
        }

        public Q<T> limit(int limit) {
            this.limit = limit;

            return this;
        }

        public T get() throws CBCException {

            return (T) client.exchange(
                    getFinalUrl(null),
                    null,
                    HttpMethod.GET,
                    null,
                    type
            );
        }

        public <S> T create(S payload) throws CBCException {
            return (T) client.exchange(
                    getFinalUrl(null),
                    payload,
                    HttpMethod.POST,
                    null,
                    type
            );
        }

        public <S> T update(S payload) throws CBCException {

            return (T) client.exchange(
                    getFinalUrl(null),
                    payload,
                    HttpMethod.PUT,
                    null,
                    type
            );
        }

        public void delete() throws CBCException {
            client.exchange(
                    getFinalUrl(null),
                    null,
                    HttpMethod.DELETE,
                    null,
                    type
            );
        }

        public T action(String action, HttpMethod method) throws CBCException {
            return (T) client.exchange(
                    getFinalUrl(action),
                    null,
                    method,
                    null,
                    type
            );
        }

        public T first() throws CBCException {
            limit = 1;

            return (T) client.exchange(
                    getFinalUrl(null),
                    null,
                    HttpMethod.GET,
                    null,
                    type
            );
        }

    }

    public Client(Config config) {
        super(config);
    }

    public <T> Q<T> newQ(T identifier) {
        return new Q<T>(this);
    }

}

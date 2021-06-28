package com.cloudblue.connect.api.clients;

import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.clients.rql.R;
import com.cloudblue.connect.api.clients.utils.RequestUtil;
import com.cloudblue.connect.api.clients.utils.Url;
import com.cloudblue.connect.api.exceptions.CBCException;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.*;

public class Client extends BaseClient {

    public static final class Q {
        private final List<String> ns = new ArrayList<>();
        private final Map<Integer, String> nsKeyMap = new HashMap<>();
        private final Map<String, String> filters = new HashMap<>();
        private final List<R> rqlFilters = new ArrayList<>();
        private final List<String> orderBys = new ArrayList<>();
        private int limit = 100;

        private final TypeReference<? extends Object> type;

        private final Client client;

        public <S> Q(Client client, TypeReference<S> typeInfo) {
            this.client = client;
            this.type = typeInfo;
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
            List<String> queryStrings = new ArrayList<>();

            if (!url.contains("?"))
                url = url.concat("?");
            else if (!url.endsWith("?"))
                url = url.concat("&");

            if (!rqlFilters.isEmpty()) {
                String rqlStr;
                if (rqlFilters.size() == 1)
                    rqlStr = rqlFilters.get(0).toString();
                else
                    rqlStr = R.and(rqlFilters.toArray(new R[]{})).toString();

                queryStrings.add(rqlStr);
            }

            if (!filters.isEmpty()) {
                queryStrings.add(RequestUtil.buildQueryParameters(filters));
            }

            if (!orderBys.isEmpty()) {
                queryStrings.add(R.orderBy(orderBys.toArray(new String[]{})).toString());
            }

            queryStrings.add("limit=" + limit);


            return url.concat(Url.encode(String.join("&", queryStrings)));
        }

        public String getFinalUrl(String action) {
            if (action != null && !action.isEmpty())
                return processFilters(getUrl() + "/" + action);
            else
                return processFilters(getUrl());
        }

        public Q collection(String ns) {
            return collection(ns, null);
        }

        public Q collection(String ns, String key) {
            this.ns.add(ns);
            this.nsKeyMap.put(this.ns.size() - 1, key);

            return this;
        }

        public Q filter(String property, String value) {
            this.filters.put(property, value);

            return this;
        }

        public Q filter(R... r) {
            this.rqlFilters.addAll(Arrays.asList(r));

            return this;
        }

        public Q orderBy(String... orderBys) {
            this.orderBys.addAll(Arrays.asList(orderBys));

            return this;
        }

        public Q limit(int limit) {
            this.limit = limit;

            return this;
        }

        public Object get() throws CBCException {

            return client.exchange(
                    getFinalUrl(null),
                    null,
                    HttpMethod.GET,
                    null,
                    type
            );
        }

        public void download(String location, String fileName) throws CBCException {
            client.download(
                    getFinalUrl(null),
                    HttpMethod.GET,
                    null,
                    location,
                    fileName
            );
        }

        public <S> Object create(S payload) throws CBCException {
            return client.exchange(
                    getFinalUrl(null),
                    payload,
                    HttpMethod.POST,
                    null,
                    type
            );
        }

        public <S> Object update(S payload) throws CBCException {

            return client.exchange(
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

        public <S> Object action(String action, HttpMethod method, S payload) throws CBCException {
            return client.exchange(
                    getFinalUrl(action),
                    payload,
                    method,
                    null,
                    type
            );
        }

        public Object first() throws CBCException {
            limit = 1;

            return client.exchange(
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

    public <T> Q newQ(TypeReference<T> identifier) {
        return new Q(this, identifier);
    }

}

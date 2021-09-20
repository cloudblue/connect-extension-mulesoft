/*
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */

package com.cloudblue.connect.internal.connection;

import com.cloudblue.connect.internal.clients.constants.APIConstants;
import com.cloudblue.connect.internal.clients.constants.ContentType;
import com.cloudblue.connect.internal.clients.constants.HeaderParams;
import com.cloudblue.connect.internal.clients.constants.RestConstants;
import com.cloudblue.connect.internal.clients.entity.FileEntity;
import com.cloudblue.connect.internal.clients.parsers.jackson.JacksonRequestMarshaller;
import com.cloudblue.connect.internal.clients.rql.R;
import com.cloudblue.connect.internal.clients.utils.RequestUtil;
import com.cloudblue.connect.internal.clients.utils.Url;
import com.cloudblue.connect.api.parameters.CBCResponseAttributes;
import com.cloudblue.connect.internal.connection.provider.CBCConnectionProvider;
import com.cloudblue.connect.internal.exception.BadRequestException;
import com.cloudblue.connect.internal.exception.ResourceNotFoundException;
import com.cloudblue.connect.internal.exception.UnauthorizedException;

import org.mule.runtime.api.exception.MuleRuntimeException;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.InputStreamHttpEntity;
import org.mule.runtime.http.api.domain.entity.multipart.HttpPart;
import org.mule.runtime.http.api.domain.entity.multipart.MultipartHttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static org.mule.runtime.api.i18n.I18nMessageFactory.createStaticMessage;
import static org.mule.runtime.http.api.HttpConstants.*;


public final class CBCConnection {

    private final HttpClient client;
    private final CBCConnectionProvider.ConnectionParams connectionParams;

    public static final class Q {
        private final HttpClient client;
        private final CBCConnectionProvider.ConnectionParams connectionParams;

        private final List<String> ns = new ArrayList<>();
        private final Map<Integer, String> nsKeyMap = new HashMap<>();
        private final Map<String, String> filters = new HashMap<>();
        private final List<R> rqlFilters = new ArrayList<>();
        private final List<String> orderBys = new ArrayList<>();
        private int limit = 100;
        private int offset = 0;
        private boolean encode = true;
        private boolean listOperation = false;

        public Q(HttpClient client, CBCConnectionProvider.ConnectionParams connectionParams) {
            this.client = client;
            this.connectionParams = connectionParams;
        }

        private String getUrl() {
            StringBuilder builder = new StringBuilder();

            String baseUrl = connectionParams.getEndpoint();
            if (baseUrl.endsWith("/")) {
                baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
            }

            builder.append(baseUrl);

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
            String urlWithFilters = url;

            if (!url.contains("?"))
                urlWithFilters = url.concat("?");
            else if (!url.endsWith("?"))
                urlWithFilters = url.concat("&");

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

            if (listOperation) {
                queryStrings.add("limit=" + limit);
                queryStrings.add("offset=" + offset);
            }

            String queryString = String.join("&", queryStrings);

            if (this.encode) {
                queryString = Url.encode(queryString);
            }

            return urlWithFilters.concat(queryString);
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

        public Q encode(boolean encode) {
            this.encode = encode;

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

        public Q offset(int offset) {
            this.offset = offset;

            return this;
        }

        public Q listOperation(boolean listOperation) {
            this.listOperation = listOperation;

            return this;
        }

        private void includeFileEntity(HttpRequestBuilder builder, FileEntity fileEntity) throws IOException {
            List<HttpPart> parts = new ArrayList<>();
            for (Map.Entry<String, File> entry : fileEntity.getFiles().entrySet()) {
                HttpPart part = new HttpPart(
                        entry.getKey(),
                        entry.getKey(),
                        Files.readAllBytes(entry.getValue().toPath()),
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                        (int)entry.getValue().length());

                parts.add(part);
            }

            for (Map.Entry<String, String> entry : fileEntity.getValues().entrySet()) {
                HttpPart part = new HttpPart(
                        entry.getKey(),
                        entry.getValue().getBytes(StandardCharsets.UTF_8),
                        "text/plain",
                        entry.getValue().length());

                parts.add(part);
            }

            builder.entity(new MultipartHttpEntity(parts));
        }

        private <S> void buildEntity(HttpRequestBuilder builder, S payload) {
            String requestBody;
            try {
                if (payload instanceof String) {
                    requestBody = (String) payload;
                    builder.addHeader(HeaderParams.CONTENT_TYPE, ContentType.JSON.getValue());
                    builder.entity(new ByteArrayHttpEntity(requestBody.getBytes()));
                } else if (payload instanceof FileEntity) {
                    includeFileEntity(builder, (FileEntity) payload);
                } else if (payload instanceof InputStream) {
                    builder.addHeader(HeaderParams.CONTENT_TYPE, ContentType.JSON.getValue());
                    builder.entity(new InputStreamHttpEntity((InputStream) payload));
                } else {
                    requestBody = new JacksonRequestMarshaller().marshal(payload);
                    builder.addHeader(HeaderParams.CONTENT_TYPE, ContentType.JSON.getValue());
                    builder.entity(new ByteArrayHttpEntity(requestBody.getBytes()));
                }
            } catch (IOException e) {
                throw new BadRequestException("Error occurred during creating request.", e);
            }
        }

        private <S> HttpRequest getRequest(Method method, String action, S payload) {

            HttpRequestBuilder builder = HttpRequest.builder()
                    .method(method)
                    .uri(getFinalUrl(action))
                    .addHeader(HeaderParams.ACCEPT, ContentType.JSON.getValue())
                    .addHeader(HeaderParams.AUTHORIZATION, connectionParams.getToken())
                    .addHeader(HeaderParams.USER_AGENT, RestConstants.USER_AGENT);


            if (payload != null)
                buildEntity(builder, payload);

            return builder.build();
        }

        private String getErrorResponseBody(HttpResponse response) {
            return new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining(""));
        }

        private void checkError(HttpResponse response) {
            int statusCode = response.getStatusCode();

            if (statusCode == 401 || statusCode == 403) {
                throw new UnauthorizedException("Either authentication token is not valid or resource access is forbidden.");
            } else if (statusCode == 404) {
                throw new ResourceNotFoundException(getErrorResponseBody(response));
            } else if (statusCode > 399 && statusCode < 500) {
                throw new BadRequestException(getErrorResponseBody(response));
            } else if (statusCode > 499) {
                throw new MuleRuntimeException(createStaticMessage(response.getReasonPhrase()));
            }
        }

        private Result<InputStream, CBCResponseAttributes> execute(HttpRequest request) {
            try {
                HttpResponse response = client.send(
                        request,
                        connectionParams.getMilSecTimeout(),
                        true,
                        null);

                checkError(response);

                Result.Builder<InputStream, CBCResponseAttributes> resultBuilder = Result.builder();

                return resultBuilder
                        .mediaType(MediaType.APPLICATION_JSON)
                        .output(response.getEntity().getContent())
                        .attributes(new CBCResponseAttributes(response))
                        .build();


            } catch (IOException | TimeoutException e) {
                throw new MuleRuntimeException(createStaticMessage("Server connection error."), e);
            }
        }

        private void downloadFile(HttpResponse response, File file) {
            try {
                InputStream inputStream = response.getEntity().getContent();

                try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
                    int read;
                    byte[] bytes = new byte[8192];
                    while ((read = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                }
            } catch (IOException e) {
                throw new BadRequestException("Error during saving file to specified location.", e);
            }
        }

        private Result<Void, CBCResponseAttributes> download(HttpRequest request, String location, String fileName) {
            try {
                HttpResponse response = client.send(
                        request,
                        connectionParams.getMilSecTimeout(),
                        true,
                        null);

                Result.Builder<Void, CBCResponseAttributes> resultBuilder = Result.builder();

                Result<Void, CBCResponseAttributes> result = resultBuilder
                        .attributes(new CBCResponseAttributes(response))
                        .build();

                String fullFileLocation = location + File.separator + fileName;
                File outputFile = new File(fullFileLocation);
                downloadFile(response, outputFile);

                return result;
            } catch (IOException | TimeoutException e) {
                throw new MuleRuntimeException(createStaticMessage("Server connection error."), e);
            }
        }

        public Result<InputStream, CBCResponseAttributes> get() {
            return this.execute(getRequest(Method.GET, null, null));
        }

        public <S> Result<InputStream, CBCResponseAttributes> create(S payload) {
            return this.execute(getRequest(Method.POST, null, payload));
        }

        public <S> Result<InputStream, CBCResponseAttributes> update(S payload) {

            return this.execute(getRequest(Method.PUT, null, payload));
        }

        public <S> Result<InputStream, CBCResponseAttributes> action(String action, Method method, S payload) {
            return this.execute(getRequest(method, action, payload));
        }

        public Result<Void, CBCResponseAttributes> download(String location, String fileName) {
            return this.download(
                    getRequest(Method.GET, null, null),
                    location,
                    fileName
            );
        }
    }

    public CBCConnection(HttpClient client, CBCConnectionProvider.ConnectionParams connectionParams) {
        this.client = client;
        this.connectionParams = connectionParams;
    }

    public Q newQ() {
        return new Q(client, connectionParams);
    }

    public boolean isConnected() {
        newQ().collection(APIConstants.CollectionKeys.ACCOUNTS).get();

        return true;
    }

}

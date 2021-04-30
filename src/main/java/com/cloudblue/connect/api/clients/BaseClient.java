package com.cloudblue.connect.api.clients;

import com.cloudblue.connect.api.models.CBCError;
import com.cloudblue.connect.api.clients.constants.HeaderParams;
import com.cloudblue.connect.api.clients.constants.HttpMethod;
import com.cloudblue.connect.api.clients.constants.HttpStatus;
import com.cloudblue.connect.api.clients.constants.RestConstants;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.api.clients.errors.ErrorCodes;
import com.cloudblue.connect.api.clients.parsers.RequestMarshaller;
import com.cloudblue.connect.api.clients.parsers.ResponseUnmarshaller;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonRequestMarshaller;
import com.cloudblue.connect.api.clients.parsers.jackson.JacksonResponseUnmarshaller;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClient.class);
    
    private final Config config;
    private final RequestMarshaller requestMarshaller;
    private final ResponseUnmarshaller responseUnmarshaller;
    private final CloseableHttpClient httpClient;
    
    public BaseClient(Config config) {
        this.config = config;
        this.requestMarshaller = new JacksonRequestMarshaller();
        this.responseUnmarshaller = new JacksonResponseUnmarshaller();
        this.httpClient = HttpClients.createDefault();
    }

    public void close(){
        try{
           if (this.httpClient !=null)this.httpClient.close();
        }catch(IOException ex){
            LOGGER.error("Unable to close HTTP client due to error details.", ex);
        }
    }

    protected HttpStatus getHttpStatusCode(HttpResponse response) throws CBCException {
        HttpStatus statusCode;
        try {
            statusCode = HttpStatus.valueOf(response.getStatusLine().getStatusCode());
        } catch (IllegalArgumentException ex) {
            throw new CBCException(
                    ErrorCodes.CONNECTAPI_ERROR.getValue(),
                    "In valid HTTP Response Code",
                    ex.getMessage()
            );
        }
        return statusCode;
    }

    protected boolean hasError(HttpStatus statusCode) {
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    protected String getResponseBody(HttpResponse response) throws CBCException {
        HttpEntity responseEntity = response.getEntity();
        String responseBody;

        try {
            responseBody = EntityUtils.toString(responseEntity, "UTF-8");
        } catch (Exception ex) {
            throw new CBCException(
                    ErrorCodes.EXTENSION_ERROR.getValue(),
                    "Failed to Parse HTTP Response",
                    ex.getMessage()
            );
        }

        return responseBody;
    }

    public HttpRequestBase getHttpRequest(HttpMethod method, String uri) throws CBCException {
        HttpRequestBase request;
        String baseUrl = this.config.getHost();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
        }
        String completeUrl = baseUrl + uri;
        switch (method) {
            case DELETE:
                request = new HttpDelete(completeUrl);
                break;
            case GET:
                request = new HttpGet(completeUrl);
                break;
            case HEAD:
                request = new HttpHead(completeUrl);
                break;
            case OPTIONS:
                request = new HttpOptions(completeUrl);
                break;
            case PATCH:
                request = new HttpPatch(completeUrl);
                break;
            case POST:
                request = new HttpPost(completeUrl);
                break;
            case PUT:
                request = new HttpPut(completeUrl);
                break;
            case TRACE:
                request = new HttpTrace(completeUrl);
                break;
            default:
                throw new CBCException(
                        ErrorCodes.EXTENSION_ERROR.getValue(),
                        "Invalid Http Method",
                        "Invalid Http Method: " + method
                );

        }
        return request;
    }

    public HttpResponse exchange(HttpRequestBase request) throws CBCException {

        CloseableHttpResponse response;
        try {
            response = this.httpClient.execute(request);
            LOGGER.trace("HTTP Response: {}", response);
        } catch (IOException ex) {
            throw new CBCException(
                    ErrorCodes.CONNECTAPI_ERROR.getValue(),
                    ex.getMessage(),
                    ex.getLocalizedMessage()
            );
        }

        HttpStatus httpStatus = getHttpStatusCode(response);

        if (httpStatus == HttpStatus.NOT_FOUND) {
            response = null;
        } else  if (hasError(httpStatus)) {
            try {
                String responseBody = getResponseBody(response);
                CBCError error = this.responseUnmarshaller.unmarshal(
                        responseBody, CBCError.class
                );
                
                if (error!=null){
                    error.setStatus(httpStatus);
                    throw new CBCException(
                            ErrorCodes.CONNECTAPI_ERROR.getValue(),
                            "API execution error",
                            error
                    );
                }
                else{
                    throw new CBCException("No error details found in HTTP response");
                }
            } catch(CBCException ex){
                throw ex;
            } catch (Exception ex) {
                throw new CBCException(
                        ErrorCodes.CONNECTAPI_ERROR.getValue(),
                        ex.getMessage(),
                        ex.getLocalizedMessage()
                );
            }
        }
        return response;
    }

    protected void setHeaders(HttpRequestBase httpRequest) {
        httpRequest.addHeader(HeaderParams.USER_AGENT, RestConstants.USER_AGENT);
        httpRequest.addHeader(HeaderParams.ACCEPT, "application/json, application/*+json");
        httpRequest.addHeader(HeaderParams.CONTENT_TYPE, "application/json");
        httpRequest.addHeader(HeaderParams.AUTHORIZATION, this.config.getToken());
    }

    public <T> HttpResponse exchange(
            String url,
            T request,
            HttpMethod method,
            Map<String, String> headers
    ) throws CBCException {

        HttpRequestBase httpRequest = getHttpRequest(method, url);

        this.setHeaders(httpRequest);

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (httpRequest instanceof HttpEntityEnclosingRequestBase && request != null) {
            HttpEntityEnclosingRequestBase enclosingRequest = (HttpEntityEnclosingRequestBase) httpRequest;
            String requestBody;
            try {
                if (request instanceof String) {
                    requestBody = (String) request;
                    StringEntity params = new StringEntity(requestBody);
                    enclosingRequest.setEntity(params);
                } else {
                    requestBody = this.requestMarshaller.marshal(request);
                    enclosingRequest.setEntity(
                            new StringEntity(requestBody, ContentType.APPLICATION_JSON)
                    );
                }

            } catch (Exception e) {
                throw new CBCException(e.getMessage());
            }
        }

        return exchange(httpRequest);
    }


    public <T, S> S exchange(
            String url, T request, 
            HttpMethod method, 
            Map<String, String> headers,
            TypeReference<S> typeInfo
    ) throws CBCException {
        Object result = null;

        HttpResponse response = exchange(url, request, method, headers);

        if (typeInfo != null && response != null) {
            try {
                String responseBody = getResponseBody(response);
                if (responseBody != null && !responseBody.isEmpty()) {
                    responseBody = responseBody.replace("\n", "");
                    result = this.responseUnmarshaller.unmarshal(responseBody, typeInfo);
                }
            } catch (Exception e) {
                throw new CBCException(e.getMessage());
            }
        }
        return (S) result;
    }

}

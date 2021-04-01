package com.cloudblue.connect.internal.connections.clients;

import com.cloudblue.connect.api.models.CBCError;
import com.cloudblue.connect.internal.connections.CBCConnectionProvider;
import com.cloudblue.connect.internal.connections.constants.HeaderParams;
import com.cloudblue.connect.internal.connections.constants.HttpMethod;
import com.cloudblue.connect.internal.connections.constants.HttpStatus;
import com.cloudblue.connect.internal.connections.constants.RestConstants;
import com.cloudblue.connect.api.exceptions.CBCException;
import com.cloudblue.connect.internal.connections.errors.ErrorCodes;
import com.cloudblue.connect.internal.connections.parsers.RequestMarshaller;
import com.cloudblue.connect.internal.connections.parsers.ResponseUnmarshaller;
import com.cloudblue.connect.internal.connections.parsers.jackson.JacksonRequestMarshaller;
import com.cloudblue.connect.internal.connections.parsers.jackson.JacksonResponseUnmarshaller;
import com.cloudblue.connect.internal.connections.utils.RequestUtil;

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

public class CBCClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CBCClient.class);
    
    private final CBCConnectionProvider.ConnectionParams config;
    private final RequestMarshaller requestMarshaller;
    private final ResponseUnmarshaller responseUnmarshaller;
    private final CloseableHttpClient client;
    
    public CBCClient(CBCConnectionProvider.ConnectionParams config) {
        this.config = config;
        this.requestMarshaller = new JacksonRequestMarshaller();
        this.responseUnmarshaller = new JacksonResponseUnmarshaller();
        this.client = HttpClients.createDefault();
    }

    public void invalidate(){
        try{
           if (this.client!=null)this.client.close();
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
        String responseBody = null;

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
            response = this.client.execute(request);
            LOGGER.trace("HTTP Response: ");
            LOGGER.trace(response.toString());
        } catch (IOException ex) {
            LOGGER.error("Failed to execute API call: ",ex);
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
                    throw new Exception("No error details found in HTTP response");
                }
            }
            catch(CBCException ex){
                LOGGER.error("Error during calling Connect API:", ex);
                throw ex;
            }
            catch (Exception ex) {
                LOGGER.error("Failed to generate error details from API error response", ex);
                throw new CBCException(
                        ErrorCodes.CONNECTAPI_ERROR.getValue(),
                        ex.getMessage(),
                        ex.getLocalizedMessage()
                );
            }
        }
        return response;
    }

    public <T, S> Object exchange(
            String url, T request,
            HttpMethod method,
            Map<String, String> headers,
            Map<String, Object> queryParams,
            TypeReference typeInfo
    ) throws CBCException {
        return exchange(
                RequestUtil.addQueryParams(url, queryParams),
                request,
                method,
                headers,
                typeInfo
        );
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
            String requestBody = null;
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
                LOGGER.trace("HTTP Request: " + requestBody);
                LOGGER.trace(httpRequest.toString());

            } catch (Exception e) {
                LOGGER.error("Failed during Connect API call", e);
                throw new CBCException(e.getMessage());
            }
        }

        HttpResponse response = exchange(httpRequest);

        return response;
    }

    public <T, S> S exchange(
            String url,
            T request,
            HttpMethod method, 
            Map<String, String> headers,
            Class<S> responseType
    ) throws CBCException {
        S result = null;

        HttpResponse response = exchange(url, request, method, headers);

        if (responseType != null && response != null) {
            try {
                if (responseType != HttpResponse.class) {
                    String responseBody = getResponseBody(response);
                    LOGGER.trace("HTTP Response: " + responseBody);
                    if (responseBody != null && !responseBody.isEmpty()) {
                        result = this.responseUnmarshaller.unmarshal(responseBody, responseType);
                    }
                } else {
                    result = (S) response;
                }
            } catch (Exception ex) {
                LOGGER.error("Unable to parse API response due to error details: ", ex);
                throw new CBCException(
                        ErrorCodes.EXTENSION_ERROR.getValue(),
                        "API response parsing error",
                        ex.getMessage()
                );
            }
        }
        
        return result;
    }

    public <T, S> Object exchange(
            String url, T request, 
            HttpMethod method, 
            Map<String, String> headers,
            TypeReference typeInfo
    ) throws CBCException {
        Object result = null;

        HttpResponse response = exchange(url, request, method, headers);

        if (typeInfo != null && response != null) {
            try {
                String responseBody = getResponseBody(response);
                responseBody = responseBody.replace("\n", "");
                if (responseBody != null && !responseBody.isEmpty()) {
                    result = this.responseUnmarshaller.unmarshal(responseBody, typeInfo);
                }
            } catch (Exception e) {
                throw new CBCException(e.getMessage());
            }
        }
        return result;
    }
}

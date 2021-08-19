package com.cloudblue.connect.api.clients;

import com.cloudblue.connect.api.clients.entity.FileEntity;
import com.cloudblue.connect.api.models.common.CBCError;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClient {
    private static final Logger logger = LoggerFactory.getLogger(BaseClient.class);
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
           if (this.httpClient !=null)
               this.httpClient.close();
        }catch(IOException ex){
            logger.error("Unable to close HTTP client due to error details.", ex);
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
                    ex.getMessage(),
                    ex
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
                    ex.getMessage(),
                    ex
            );
        }

        return responseBody;
    }

    private String getCompleteUrl(String uri) {
        String baseUrl = this.config.getHost();
        if (baseUrl.endsWith("/")) {
            baseUrl = baseUrl.substring(0, baseUrl.lastIndexOf("/"));
        }
        return baseUrl + uri;
    }

    public HttpRequestBase getHttpRequest(HttpMethod method, String uri) throws CBCException {
        HttpRequestBase request;

        String completeUrl = getCompleteUrl(uri);

        switch (method) {
            case DELETE:
                request = new HttpDelete(completeUrl);
                break;
            case GET:
                request = new HttpGet(completeUrl);
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
            default:
                throw new CBCException(
                        ErrorCodes.EXTENSION_ERROR.getValue(),
                        "Invalid Http Method",
                        "Invalid Http Method: " + method
                );

        }
        return request;
    }

    private void handleError(HttpStatus httpStatus, CloseableHttpResponse response) throws CBCException {
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
                    ex.getLocalizedMessage(),
                    ex
            );
        }
    }

    public HttpResponse exchange(HttpRequestBase request) throws CBCException {

        CloseableHttpResponse response;
        try {
            response = this.httpClient.execute(request);
            logger.trace("HTTP Response: {}", response);
        } catch (IOException ex) {
            throw new CBCException(
                    ErrorCodes.CONNECTAPI_ERROR.getValue(),
                    ex.getMessage(),
                    ex.getLocalizedMessage(),
                    ex
            );
        }

        HttpStatus httpStatus = getHttpStatusCode(response);

        if (httpStatus == HttpStatus.NOT_FOUND) {
            response = null;
        } else  if (hasError(httpStatus)) {
            handleError(httpStatus, response);
        }
        return response;
    }

    protected void setHeaders(HttpRequestBase httpRequest) {
        httpRequest.addHeader(HeaderParams.USER_AGENT, RestConstants.USER_AGENT);
        httpRequest.addHeader(HeaderParams.ACCEPT, "application/json, application/*+json");
        httpRequest.addHeader(HeaderParams.AUTHORIZATION, this.config.getToken());
    }

    private void encloseFileEntity(FileEntity request, HttpEntityEnclosingRequestBase enclosingRequest) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        for (Map.Entry<String, File> entry : request.getFiles().entrySet())
            builder.addPart(entry.getKey(), new FileBody(entry.getValue()));

        for (Map.Entry<String, String> entry: request.getValues().entrySet())
            builder.addTextBody(entry.getKey(), entry.getValue());

        enclosingRequest.setEntity(builder.build());
    }

    private <T> void handleEntityRequest(T request, HttpRequestBase httpRequest) throws CBCException {
        HttpEntityEnclosingRequestBase enclosingRequest = (HttpEntityEnclosingRequestBase) httpRequest;
        String requestBody;
        try {
            if (request instanceof String) {
                requestBody = (String) request;
                StringEntity params = new StringEntity(requestBody);
                enclosingRequest.setEntity(params);
            } else if (request instanceof FileEntity) {
                encloseFileEntity((FileEntity) request, enclosingRequest);
            } else {
                requestBody = this.requestMarshaller.marshal(request);
                httpRequest.addHeader(HeaderParams.CONTENT_TYPE, "application/json");
                enclosingRequest.setEntity(
                        new StringEntity(requestBody, ContentType.APPLICATION_JSON)
                );
            }
        } catch (Exception e) {
            throw new CBCException(e.getMessage(), e);
        }
    }

    public <T> HttpResponse exchange(
            String url,
            T request,
            HttpMethod method,
            Map<String, String> headers
    ) throws CBCException {

        HttpRequestBase httpRequest = getHttpRequest(method, url);
        logger.trace("Request: {}", httpRequest);
        this.setHeaders(httpRequest);

        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpRequest.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (httpRequest instanceof HttpEntityEnclosingRequestBase && request != null) {
            handleEntityRequest(request, httpRequest);
        }

        return exchange(httpRequest);
    }

    private <S> S getResult(HttpResponse response, TypeReference<S> typeInfo) throws CBCException {
        S result = null;
        try {
            String responseBody = getResponseBody(response);
            if (responseBody != null && !responseBody.isEmpty()) {
                responseBody = responseBody.replace("\n", "");
                result = (S) this.responseUnmarshaller.unmarshal(responseBody, typeInfo);
            }
        } catch (Exception e) {
            throw new CBCException(e.getMessage(), e);
        }

        return result;
    }


    public <T, S> S exchange(
            String url, T request, 
            HttpMethod method, 
            Map<String, String> headers,
            TypeReference<S> typeInfo
    ) throws CBCException {

        HttpResponse response = exchange(url, request, method, headers);

        if (typeInfo != null && response != null) {
            return getResult(response, typeInfo);
        }
        return null;
    }

    public void download(
            String url,
            HttpMethod method,
            Map<String, String> headers,
            String location,
            String fileName
    ) throws CBCException {
        HttpResponse response = exchange(url, null, method, headers);


        HttpEntity entity = response.getEntity();
        try {
            String fullFileLocation = location + File.separator + fileName;
            File myFile = new File(fullFileLocation);
            if (myFile.createNewFile()) {
                if (entity != null) {
                    try (FileOutputStream outStream = new FileOutputStream(myFile)) {
                        entity.writeTo(outStream);
                    }
                }
            } else {
                logger.error("Error during creation of file {}.", fullFileLocation);
                throw new CBCException("Not able to create file " + fullFileLocation);
            }

        } catch (IOException e) {
            throw new CBCException("Not able to download file.", e);
        }

    }

}

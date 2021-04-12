package com.cloudblue.connect.api.clients.parsers;

public interface ResponseUnmarshaller {
    <T> T unmarshal(String stringResponse, Class<T> responseType) throws Exception;

    Object unmarshal(String stringResponse, Object typeInfo) throws Exception;
}

package com.cloudblue.connect.api.clients.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ResponseUnmarshaller {
    <T> T unmarshal(String stringResponse, Class<T> responseType) throws JsonProcessingException;

    Object unmarshal(String stringResponse, Object typeInfo) throws JsonProcessingException;
}

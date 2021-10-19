/**
 * Copyright © 2021 Ingram Micro Inc. All rights reserved.
 * The software in this package is published under the terms of the Apache-2.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE file.
 */
package com.cloudblue.connect.internal.clients.parsers.jackson;

import com.cloudblue.connect.internal.clients.parsers.ResponseUnmarshaller;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonResponseUnmarshaller implements ResponseUnmarshaller {

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return objectMapper;
    }

    @Override
    public <T> T unmarshal(String stringResponse, Class<T> responseType) throws JsonProcessingException {
        if (responseType == String.class && stringResponse.startsWith("[")) {
            return (T) stringResponse;
        }
        return getObjectMapper().readValue(stringResponse, responseType);
    }

    @Override
    public Object unmarshal(String stringResponse, Object typeInfo) throws JsonProcessingException {
        if (typeInfo instanceof TypeReference) {
            return getObjectMapper().readValue(stringResponse, (TypeReference) typeInfo);
        } else {
            return null;
        }
    }
}

package com.cloudblue.connect.api.clients.parsers.jackson;

import com.cloudblue.connect.api.clients.parsers.ResponseUnmarshaller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonResponseUnmarshaller implements ResponseUnmarshaller {

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return objectMapper;
    }

    @Override
    public <T> T unmarshal(String stringResponse, Class<T> responseType) throws Exception {
        if (!stringResponse.isEmpty() && !stringResponse.startsWith("{")
                && !stringResponse.startsWith("[") && responseType != String.class) {
            stringResponse = stringResponse.substring(stringResponse.indexOf("{"));
        } else if (responseType == String.class && stringResponse.startsWith("[")) {
            return (T) stringResponse;
        }
        return getObjectMapper().readValue(stringResponse, responseType);
    }

    @Override
    public Object unmarshal(String stringResponse, Object typeInfo) throws Exception {
        if (typeInfo instanceof TypeReference) {
            if (!stringResponse.isEmpty() && !stringResponse.startsWith("{") && !stringResponse.startsWith("[")) {
                stringResponse = stringResponse.substring(stringResponse.indexOf("{"));
            }
            return getObjectMapper().readValue(stringResponse, (TypeReference) typeInfo);
        } else {
            return null;
        }
    }
}

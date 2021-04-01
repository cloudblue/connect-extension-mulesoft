package com.cloudblue.connect.internal.connections.parsers.jackson;

import com.cloudblue.connect.internal.connections.parsers.RequestMarshaller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonRequestMarshaller implements RequestMarshaller {

    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        return objectMapper;
    }

    @Override
    public String marshal(Object request) throws Exception {
        return getObjectMapper().writeValueAsString(request);
    }
}

package com.cloudblue.connect.api.clients.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RequestMarshaller {
    String marshal(Object request) throws JsonProcessingException;
}

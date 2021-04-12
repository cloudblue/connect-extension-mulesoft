package com.cloudblue.connect.api.clients.parsers;

public interface RequestMarshaller {
    String marshal(Object request) throws Exception;
}

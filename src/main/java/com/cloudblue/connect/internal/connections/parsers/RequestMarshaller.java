package com.cloudblue.connect.internal.connections.parsers;

public interface RequestMarshaller {
    String marshal(Object request) throws Exception;
}

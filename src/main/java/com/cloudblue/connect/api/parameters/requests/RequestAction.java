package com.cloudblue.connect.api.parameters.requests;

import com.cloudblue.connect.api.parameters.Embeddable;
import com.cloudblue.connect.api.parameters.common.ResourceActionParameter;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestAction extends ResourceActionParameter implements Embeddable {
    Map<String, String> payload = new HashMap<>();

    public abstract String action();

    public void addValue(String key, String value) {
        payload.put(key, value);
    }
}

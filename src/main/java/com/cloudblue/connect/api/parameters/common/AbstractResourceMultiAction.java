package com.cloudblue.connect.api.parameters.common;

import com.cloudblue.connect.api.parameters.Embeddable;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResourceMultiAction extends ResourceActionParameter implements Embeddable {
    protected Map<String, String> payload = new HashMap<>();

    public abstract String action();

    public void addValue(String key, String value) {
        payload.put(key, value);
    }
}

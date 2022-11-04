package com.rzandjavagit.prohttprequest.http;

import java.util.HashMap;
import java.util.Map;

public class Property {
    private String key;
    private String value;
    private Map<String, String> params = new HashMap<>();

    public Property() {
        params = new HashMap<>();
        params.clear();
    }

    public Property setProperty(String argKey, String argValue) {
        params.put(argKey, argValue);
        return this;
    }

    public Property setProperty(Map<String, String> argParams) {
        //HashMap<String, String> temp = new HashMap<>(params);
        params = new HashMap<>(params);
        params.putAll(argParams);
        return this;
    }

    public Map<String, String> rawProperty() {
        return params;
    }
}

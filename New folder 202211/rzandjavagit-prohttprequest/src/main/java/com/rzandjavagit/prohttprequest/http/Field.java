package com.rzandjavagit.prohttprequest.http;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private String key;
    private String value;
    private Map<String, String> params = new HashMap<>();

    public Field() {
        //System.out.println("========================|CONSTRUCTOR_FIELD");
        params = new HashMap<>();
        params.clear();
    }

    public Field setField(String argKey, String argValue) {
        params.put(argKey, argValue);
        return this;
    }

    public Field setField(Map<String, String> argParams) {
        //HashMap<String, String> temp = new HashMap<>(params);
        params = new HashMap<>(params);
        params.putAll(argParams);
        return this;
    }

    public Map<String, String> rawField() {
        return params;
    }
}

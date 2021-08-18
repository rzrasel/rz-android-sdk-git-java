package com.rzandjavagit.prohttprequest.http;

import java.util.HashMap;
import java.util.Map;

public class Query {
    private String key;
    private String value;
    private Map<String, String> params = new HashMap<>();

    public Query() {
        params = new HashMap<>();
        params.clear();
    }

    public Query setQuery(String argKey, String argValue) {
        params.put(argKey, argValue);
        return this;
    }

    public Query setQuery(Map<String, String> argParams) {
        //HashMap<String, String> temp = new HashMap<>(params);
        params = new HashMap<>(params);
        params.putAll(argParams);
        return this;
    }

    public Map<String, String> rawQuery() {
        return params;
    }
}

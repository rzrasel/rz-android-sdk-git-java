package com.rzandjavagit.prohttprequest.http;

import java.util.HashMap;
import java.util.Map;

public class Header {
    //private Parameter parent;
    private String key;
    private String value;
    private Map<String, String> params = new HashMap<>();

    public Header() {
        params = new HashMap<>();
        params.clear();
    }

    public Header setHeader(String argKey, String argValue) {
        params.put(argKey, argValue);
        return this;
    }

    public Header setHeader(Map<String, String> argParams) {
        //HashMap<String, String> temp = new HashMap<>(params);
        params = new HashMap<>(params);
        params.putAll(argParams);
        return this;
    }

    public Map<String, String> rawHeader() {
        return params;
    }
    /*public Parameter backTo() {
        return parent;
    }*/
}

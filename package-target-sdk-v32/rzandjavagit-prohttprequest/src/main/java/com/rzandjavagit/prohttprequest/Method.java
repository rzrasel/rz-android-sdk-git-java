package com.rzandjavagit.prohttprequest;

public enum Method {
    DEPRECATED_GET_OR_POST("-1"),
    DELETE("DELETE"),
    GET("GET"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PATCH("PATCH"),
    POST("POST"),
    PUT("PUT"),
    TRACE("TRACE"),
    EMPTY("EMPTY");
    private String value;

    private Method(String argValue) {
        this.value = argValue;
    }

    public static Method enumOf(String argValue) {
        for (Method item : values()) {
            if (item.value.equalsIgnoreCase(argValue)) {
                return item;
            }
        }
        return null;
    }

    public static String valueOf(Method argMethod) {
        return argMethod.value;
    }
}

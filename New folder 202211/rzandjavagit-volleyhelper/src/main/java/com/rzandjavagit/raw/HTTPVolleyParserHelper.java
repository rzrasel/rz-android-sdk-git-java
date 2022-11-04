package com.rzandjavagit.raw;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class HTTPVolleyParserHelper<T> {
    protected static <T> ArrayList<T> getModel(Class<T> argClass) {
        ArrayList<T> arrayList = new ArrayList<T>();
        return arrayList;
    }

    protected static <T> void setModel(Class<T> argClass) {
        ArrayList<T> arrayList = new ArrayList<T>();
    }

    protected static <T> Type getTypeToken(Class<T> argTypeToken) {
        /*Type typeToken = new TypeToken<ArrayList<ModelUsual>>() {
        }.getType();*/
        return new TypeToken<ArrayList<T>>() {
        }.getType();
    }
}
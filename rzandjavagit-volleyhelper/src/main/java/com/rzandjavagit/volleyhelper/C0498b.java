package com.rzandjavagit.volleyhelper;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

class C0498b<T> {

    protected static <T> ArrayList<T> m1947a(Class<T> cls) {
        return new ArrayList();
    }

    protected static <T> Type m1948b(Class<T> cls) {
        return TypeToken.getParameterized(ArrayList.class, cls).getType();
    }
}

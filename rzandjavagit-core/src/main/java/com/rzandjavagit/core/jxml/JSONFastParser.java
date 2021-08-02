package com.rzandjavagit.core.jxml;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSONFastParser {

    public static HashMap<String, Object> JSONObjectFeed(String argJsonObject) throws JSONException {
        return C0367a.m1387a(argJsonObject);
    }

    public static List<Object> JSONArrayFeed(String argJsonArray) throws JSONException {
        return C0367a.m1392b(argJsonArray);
    }

    public static boolean isMap(Object argObject) {
        return C0367a.m1390a(argObject);
    }

    public static boolean isList(Object argObject) {
        return C0367a.m1393b(argObject);
    }

    public static Object getObjectByKey(HashMap<String, ?> argObjectHashMap, String argKey) {
        return C0367a.m1386a(argObjectHashMap, argKey);
    }

    public static <T> HashMap<String, T> getHashMapByKey(HashMap<String, ?> argObjectHashMap, String argKey) {
        return C0367a.m1391b(argObjectHashMap, argKey);
    }

    public static <E> ArrayList<HashMap<String, ?>> getArrayListMapByKey(HashMap<String, ?> argObjectHashMap, String argKey) {
        return C0367a.m1394c(argObjectHashMap, argKey);
    }

    public static <T> ArrayList<?> getArrayListByKey(HashMap<String, ?> argObjectHashMap, String argKey) {
        return C0367a.m1395d(argObjectHashMap, argKey);
    }
}

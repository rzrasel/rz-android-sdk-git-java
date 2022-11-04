package com.rzandjavagit.core.jxml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class C0367a {

    protected static HashMap<String, Object> m1387a(String str) throws JSONException {
        return m1388a(new JSONObject(str));
    }

    protected static List<Object> m1392b(String str) throws JSONException {
        return m1389a(new JSONArray(str));
    }

    private static HashMap<String, Object> m1388a(JSONObject jSONObject) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONArray) {
                obj = m1389a((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = m1388a((JSONObject) obj);
            }
            hashMap.put(str, obj);
        }
        return hashMap;
    }

    private static List<Object> m1389a(JSONArray jSONArray) throws JSONException {
        List<Object> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONArray) {
                obj = m1389a((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = m1388a((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    protected static boolean m1390a(Object obj) {
        if ((obj instanceof HashMap) || (obj instanceof Map)) {
            return true;
        }
        return false;
    }

    protected static boolean m1393b(Object obj) {
        if ((obj instanceof ArrayList) || (obj instanceof List)) {
            return true;
        }
        return false;
    }

    protected static <T> T m1386a(HashMap<String, ?> hashMap, String str) {
        return hashMap.containsKey(str) ? (T) hashMap.get(str) : null;
    }

    protected static <T> T m1391b(HashMap<String, ?> hashMap, String str) {
        if (!hashMap.containsKey(str)) {
            return null;
        }
        Object obj = hashMap.get(str);
        return m1390a(obj) ? (T) obj : null;
    }

    protected static <T> T m1394c(HashMap<String, ?> hashMap, String str) {
        if (hashMap.containsKey(str)) {
            Object obj = hashMap.get(str);
            if (m1393b(obj)) {
                ArrayList arrayList = (ArrayList) obj;
                return (arrayList.size() <= 0 || !m1390a(arrayList.get(0))) ? null : (T) arrayList;
            }
        }
        return null;
    }

    protected static <T> T m1395d(HashMap<String, ?> hashMap, String str) {
        if (!hashMap.containsKey(str)) {
            return null;
        }
        Object obj = hashMap.get(str);
        return m1393b(obj) ? (T) obj : null;
    }
}

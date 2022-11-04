package com.rzandjavagit.core.jxml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

class C0367c {
    protected static Object m1386a(Object obj) throws JSONException {
        if (obj instanceof HashMap) {
            JSONObject jSONObject = new JSONObject();
            HashMap hashMap = (HashMap) obj;
            for (Object next : hashMap.keySet()) {
                jSONObject.put(next.toString(), m1386a(hashMap.get(next)));
            }
            return jSONObject;
        } else if ((obj instanceof Iterable)) {
            JSONArray jSONArray = new JSONArray();
            for (Object a : (Iterable) obj) {
                jSONArray.put(m1386a(a));
            }
            return jSONArray;
        } else {
            return obj;
        }
    }

    protected static boolean m1387a(JSONObject jSONObject) {
        return jSONObject.names() == null;
    }
}

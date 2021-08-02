package com.rzandjavagit.volleyhelper;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class C0497a {
    private static C0497a f1740b;
    private Context f1741a;
    private RequestQueue f1742c;
    private HTTPVolleyHelper.OnResponseListenerHandler f1743d;
    private HashMap<String, String> f1744e;
    private HashMap<String, String> f1745f;
    private String f1746g;
    private Class f1747h;

    protected C0497a(Context context) {
        this.f1741a = context;
        this.f1744e = new HashMap();
        this.f1745f = new HashMap();
        this.f1744e.clear();
        this.f1745f.clear();
        this.f1742c = m1926a();
    }

    private RequestQueue m1926a() {
        if (f1742c == null) {
            Cache cache = new DiskBasedCache(this.f1741a.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            f1742c = new RequestQueue(cache, network);
        }
        return this.f1742c;
    }

    protected C0497a m1937a(HTTPVolleyHelper.OnResponseListenerHandler c0499a) {
        this.f1743d = c0499a;
        return this;
    }

    protected C0497a m1939a(String str) {
        this.f1746g = str;
        return this;
    }

    protected C0497a m1940a(String str, String str2) {
        this.f1744e.put(str, str2);
        return this;
    }

    protected C0497a m1941a(HashMap<String, String> hashMap) {
        this.f1744e = hashMap;
        return this;
    }

    protected C0497a m1944a(String str, String str2) {
        this.f1745f.put(str, str2);
        return this;
    }

    protected C0497a m1945b(HashMap<String, String> hashMap) {
        this.f1745f = hashMap;
        return this;
    }

    protected C0497a m1938a(Class<?> cls) {
        this.f1747h = cls;
        return this;
    }

    protected void m1942a(HTTPVolleyHelper.HTTPMethod argRequestMethod) {
        if (this.f1746g == null) {
            m1936d("REQUEST_URL (101): NULL");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST_URL (101): ");
        stringBuilder.append(this.f1746g);
        m1936d(stringBuilder.toString());
        StringRequest stringPostRequest = new StringRequest(argRequestMethod.getValue(), this.f1746g,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String argResponse) {
                        m1935c(argResponse);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        m1929a(argVolleyError);
                    }
                }
        ) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse argNetworkResponse) {
                try {
                    String responseData = new String(argNetworkResponse.data, HttpHeaderParser.parseCharset(argNetworkResponse.headers));
                    return Response.success(responseData, HttpHeaderParser.parseCacheHeaders(argNetworkResponse));
                } catch (UnsupportedEncodingException ex) {
                    return Response.error(new ParseError(ex));
                } catch (JsonSyntaxException ex) {
                    return Response.error(new ParseError(ex));
                }

            }

            @Override
            public Map<String, String> getHeaders() {
                if (f1744e == null) {
                    f1744e = new HashMap<String, String>();
                    //return f1744e;
                }
                //f1744e.put("Content-Type", "application/json");
                Map<String, String> treeMap = new TreeMap<String, String>(f1744e);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_HEADERS (101): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }

            @Override
            protected Map<String, String> getParams() {
                if (f1745f == null) {
                    f1745f = new HashMap<>();
                    //return f1745f;
                }
                Map<String, String> treeMap = new TreeMap<String, String>(f1745f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_PARAMETERS (101): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }
        };
        this.f1742c.add(stringPostRequest);
        this.f1742c.start();
    }

    protected void m1944a(JSONObject jSONObject) {
        if (this.f1746g == null) {
            m1936d("REQUEST_URL (102): NULL");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST_URL (102): ");
        stringBuilder.append(this.f1746g);
        m1936d(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DEBUG_LOG_PRINT (HTTPVolleyHelper) (102): ");
        stringBuilder.append(jSONObject.toString());
        m1936d(stringBuilder.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, this.f1746g, jSONObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        m1935c(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        m1929a(argVolleyError);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                if (f1744e == null) {
                    f1744e = new HashMap<String, String>();
                    //return f1744e;
                }
                //f1744e.put("Content-Type", "application/json");
                Map<String, String> treeMap = new TreeMap<String, String>(f1744e);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_HEADERS (102): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }

            @Override
            protected Map<String, String> getParams() {
                if (f1745f == null) {
                    f1745f = new HashMap<>();
                    //return f1745f;
                }
                Map<String, String> treeMap = new TreeMap<String, String>(f1745f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_PARAMETERS (102): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }
        };
        this.f1742c.add(jsonObjectRequest);
        this.f1742c.start();
    }

    protected void m1943a(JSONArray jSONArray) {
        if (this.f1746g == null) {
            m1936d("REQUEST_URL (103): NULL");
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST_URL (103): ");
        stringBuilder.append(this.f1746g);
        m1936d(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DEBUG_LOG_PRINT (HTTPVolleyRequest) (103): ");
        stringBuilder.append(jSONArray.toString());
        m1936d(stringBuilder.toString());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(1, this.f1746g, jSONArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        m1935c(jsonArray.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        m1929a(argVolleyError);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                if (f1744e == null) {
                    f1744e = new HashMap<String, String>();
                    //return f1744e;
                }
                //f1744e.put("Content-Type", "application/json");
                Map<String, String> treeMap = new TreeMap<String, String>(f1744e);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_HEADERS (103): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }

            @Override
            protected Map<String, String> getParams() {
                if (f1745f == null) {
                    f1745f = new HashMap<>();
                    //return f1745f;
                }
                Map<String, String> treeMap = new TreeMap<String, String>(f1745f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_PARAMETERS (103): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }
        };
        this.f1742c.add(jsonArrayRequest);
        this.f1742c.start();
    }

    protected void m1946b(String str) {
        if (this.f1746g == null) {
            m1936d("REQUEST_URL (104): NULL");
            return;
        }
        JSONObject jSONObject;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("REQUEST_URL (104): ");
        stringBuilder.append(this.f1746g);
        m1936d(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("DEBUG_LOG_PRINT (HTTPVolleyRequest) (104): ");
        stringBuilder.append(str);
        m1936d(stringBuilder.toString());
        try {
            jSONObject = new JSONObject(str.replaceAll("\"\\[", "[").replaceAll("\\]\"", "]"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            m1936d("JSON is null from onJSONObjectForceRequest (104)");
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, this.f1746g, jSONObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        m1935c(jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        m1929a(argVolleyError);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                if (f1744e == null) {
                    f1744e = new HashMap<String, String>();
                    //return f1744e;
                }
                //f1744e.put("Content-Type", "application/json");
                Map<String, String> treeMap = new TreeMap<String, String>(f1744e);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_HEADERS (104): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }

            @Override
            protected Map<String, String> getParams() {
                if (f1745f == null) {
                    f1745f = new HashMap<>();
                    //return f1745f;
                }
                Map<String, String> treeMap = new TreeMap<String, String>(f1745f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_PARAMETERS (104): ");
                stringBuilder.append(treeMap.toString());
                m1936d(stringBuilder.toString());
                return treeMap;
            }
        };
        this.f1742c.add(jsonObjectRequest);
        this.f1742c.start();
    }

    private void m1935c(String str) {
        try {
            String str2 = new String(str.toString().getBytes("ISO-8859-1"), "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RESPONSE_DATA: ");
            stringBuilder.append(str2);
            m1936d(stringBuilder.toString());
            if (this.f1747h != null) {
                Type typeToken = C0498b.m1948b(this.f1747h);
                ArrayList<?> modelDataArrayList = C0498b.m1947a(this.f1747h);
                modelDataArrayList = new GsonBuilder().create().fromJson(str2.toString(), typeToken);
                if (f1743d != null) {
                    f1743d.onSuccess(modelDataArrayList);
                }
            } else if (this.f1743d != null) {
                this.f1743d.onSuccess(str2);
            }
        } catch (UnsupportedEncodingException e) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("ERROR: ");
            stringBuilder2.append(e.getMessage());
            m1936d(stringBuilder2.toString());
            if (this.f1743d != null) {
                this.f1743d.onError(new VolleyError(e.getMessage()));
            }
        }
    }

    private void m1929a(VolleyError c0437u) {
        String str = "";
        String str2 = "";
        if (c0437u.networkResponse != null) {
            str2 = c0437u.networkResponse.statusCode + "";
            try {
                str = new String(c0437u.networkResponse.data, "UTF-8");
                m1936d("ERROR: STATUS_CODE: " + str2 + " MESSAGE: " + str);
            } catch (UnsupportedEncodingException ex) {
                m1936d("ERROR: " + ex.getMessage());
                if (this.f1743d != null) {
                    this.f1743d.onError(new VolleyError(ex.getMessage()));
                }
            }
        }
        if (this.f1743d != null) {
            this.f1743d.onError(c0437u);
            this.f1743d.onError(c0437u, str2, str);
        }
    }

    private void m1936d(String str) {
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LOG_WRITER_CORE_HTTPVolleyRequest: ");
        stringBuilder.append(str);
        printStream.println(stringBuilder.toString());
    }
}

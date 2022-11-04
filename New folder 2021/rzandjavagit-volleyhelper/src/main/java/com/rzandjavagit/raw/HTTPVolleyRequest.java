package com.rzandjavagit.raw;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HTTPVolleyRequest {
    private Context context;
    private static HTTPVolleyRequest instance = null;
    private RequestQueue volleyRequest;
    private EventListenerHandler eventListener;
    private HashMap<String, String> headerParams = null;
    private HashMap<String, String> urlParameters = null;
    private String requestURL = null;

    public synchronized static HTTPVolleyRequest getInstance(Context argContext) {
        if (instance == null) {
            instance = new HTTPVolleyRequest(argContext);
        }
        return instance;
    }

    public HTTPVolleyRequest(Context argContext) {
        context = argContext;
        //volleyRequest = Volley.newRequestQueue(context);
        volleyRequest = getRequestQueue();
    }

    public HTTPVolleyRequest setURL(String argRequestURL) {
        requestURL = argRequestURL;
        //LogWriter.Log("(HTTPVolleyRequest) - setURL URL " + requestURL);
        return this;
    }

    public HTTPVolleyRequest setEventListener(EventListenerHandler argEventListener) {
        eventListener = argEventListener;
        return this;
    }

    public HTTPVolleyRequest setHeaderParameters(HashMap<String, String> argHeaderParameters) {
        headerParams = argHeaderParameters;
        return this;
    }

    public HTTPVolleyRequest setURLParameters(HashMap<String, String> argURLParameters) {
        urlParameters = argURLParameters;
        /*urlParameters.put(AppConstant.Server.FIELD_PARAMS.AUTH_KEY, "");
        urlParameters.put(AppConstant.Server.FIELD_PARAMS.PACKAGE_NAME, context.getPackageName());
        urlParameters.put(AppConstant.Server.FIELD_PARAMS.APP_VERSION, getAppVersion(context));*/
        return this;
    }

    public RequestQueue getRequestQueue() {
        if (volleyRequest == null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            volleyRequest = new RequestQueue(cache, network);
            // Don't forget to start the volley request queue
            volleyRequest.start();
        }
        return volleyRequest;
    }

    /**
     * Does some thing in old style.
     *
     * @deprecated use { @link #new()} instead.
     */
    @Deprecated
    public void onExecute(HTTPMethod argRequestMethod) {
        //Request.Method.POST
        if (urlParameters != null) {
            //LogWriter.Log("(HTTPVolleyRequest) - onExecute param size " + urlParameters.size());
        } else {
            //LogWriter.Log("(HTTPVolleyRequest) - onExecute param size null");
        }
        StringRequest stringPostRequest = new StringRequest(argRequestMethod.getValue(), requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String argResponse) {
                        VolleyLog.wtf(argResponse, "utf-8");
                        try {
                            byte[] byteArray = argResponse.toString().getBytes("ISO-8859-1");
                            argResponse = new String(byteArray, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                        }
                        //LogWriter.Log("(HTTPVolleyRequest) - onExecute Response " + argResponse);
                        if (eventListener != null) {
                            eventListener.onResponse(argResponse);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        onVolleyErrorHandler(argVolleyError);
                    }
                }
        ) {
            /*@Override
            protected Response parseNetworkResponse(NetworkResponse argNetworkResponse) {
                return Response.success(argNetworkResponse.statusCode, HttpHeaderParser.parseCacheHeaders(argNetworkResponse));
            }*/
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse argNetworkResponse) {
                /*int statusCode = argNetworkResponse.statusCode;
                System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): NetworkResponseCode " + statusCode);
                return super.parseNetworkResponse(argNetworkResponse);*/
                try {
                    String responseData = new String(argNetworkResponse.data, HttpHeaderParser.parseCharset(argNetworkResponse.headers));
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse responseData " + responseData);
                    return Response.success(responseData, HttpHeaderParser.parseCacheHeaders(argNetworkResponse));
                } catch (UnsupportedEncodingException e) {
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse UnsupportedEncodingException " + e);
                    return Response.error(new ParseError(e));
                } catch (JsonSyntaxException e) {
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse JsonSyntaxException " + e);
                    return Response.error(new ParseError(e));
                }

            }

            @Override
            public Map<String, String> getHeaders() {
                if (headerParams == null) {
                    headerParams = new HashMap<String, String>();
                    return headerParams;
                }
                //headerParams.put("Content-Type", "application/json; indent=4");
                //headers.put("Content-Type", "application/json; charset=utf-8");
                headerParams.put("Content-Type", "application/x-www-form-urlencoded");
                //headerParams.put("Content-Type", "application/json");
                Map<String, String> treeURLParameters = new TreeMap<String, String>(headerParams);
                return treeURLParameters;
            }

            @Override
            protected Map<String, String> getParams() {
                if (urlParameters == null) {
                    urlParameters = new HashMap<>();
                }
                /*urlParameters.put(AppConstant.Server.FIELD_PARAMS.AUTH_KEY, "");
                urlParameters.put(AppConstant.Server.FIELD_PARAMS.PACKAGE_NAME, context.getPackageName());
                urlParameters.put(AppConstant.Server.FIELD_PARAMS.APP_VERSION, getAppVersion(context));*/
                Map<String, String> treeURLParameters = new TreeMap<String, String>(urlParameters);
                return treeURLParameters;
            }
        };
        volleyRequest.add(stringPostRequest);
        volleyRequest.start();
    }

    public void onStringRequest(HTTPMethod argRequestMethod) {
        //Request.Method.POST
        if (urlParameters != null) {
            //LogWriter.Log("(HTTPVolleyRequest) - onExecute param size " + urlParameters.size());
        } else {
            //LogWriter.Log("(HTTPVolleyRequest) - onExecute param size null");
        }
        StringRequest stringPostRequest = new StringRequest(argRequestMethod.getValue(), requestURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String argResponse) {
                        VolleyLog.wtf(argResponse, "utf-8");
                        try {
                            byte[] byteArray = argResponse.toString().getBytes("ISO-8859-1");
                            argResponse = new String(byteArray, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                        }
                        //LogWriter.Log("(HTTPVolleyRequest) - onExecute Response " + argResponse);
                        if (eventListener != null) {
                            eventListener.onResponse(argResponse);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        onVolleyErrorHandler(argVolleyError);
                    }
                }
        ) {
            /*@Override
            protected Response parseNetworkResponse(NetworkResponse argNetworkResponse) {
                return Response.success(argNetworkResponse.statusCode, HttpHeaderParser.parseCacheHeaders(argNetworkResponse));
            }*/
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse argNetworkResponse) {
                /*int statusCode = argNetworkResponse.statusCode;
                System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): NetworkResponseCode " + statusCode);
                return super.parseNetworkResponse(argNetworkResponse);*/
                try {
                    String responseData = new String(argNetworkResponse.data, HttpHeaderParser.parseCharset(argNetworkResponse.headers));
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse responseData " + responseData);
                    return Response.success(responseData, HttpHeaderParser.parseCacheHeaders(argNetworkResponse));
                } catch (UnsupportedEncodingException e) {
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse UnsupportedEncodingException " + e);
                    return Response.error(new ParseError(e));
                } catch (JsonSyntaxException e) {
                    //LogWriter.Log("(HTTPVolleyRequest) - parseNetworkResponse JsonSyntaxException " + e);
                    return Response.error(new ParseError(e));
                }

            }

            @Override
            public Map<String, String> getHeaders() {
                if (headerParams == null) {
                    headerParams = new HashMap<String, String>();
                    return headerParams;
                }
                //headerParams.put("Content-Type", "application/json; indent=4");
                //headers.put("Content-Type", "application/json; charset=utf-8");
                headerParams.put("Content-Type", "application/x-www-form-urlencoded");
                //headerParams.put("Content-Type", "application/json");
                Map<String, String> treeURLParameters = new TreeMap<String, String>(headerParams);
                return treeURLParameters;
            }

            @Override
            protected Map<String, String> getParams() {
                if (urlParameters == null) {
                    urlParameters = new HashMap<>();
                }
                /*urlParameters.put(AppConstant.Server.FIELD_PARAMS.AUTH_KEY, "");
                urlParameters.put(AppConstant.Server.FIELD_PARAMS.PACKAGE_NAME, context.getPackageName());
                urlParameters.put(AppConstant.Server.FIELD_PARAMS.APP_VERSION, getAppVersion(context));*/
                Map<String, String> treeURLParameters = new TreeMap<String, String>(urlParameters);
                return treeURLParameters;
            }
        };
        //RequestQueue
        volleyRequest.add(stringPostRequest);
        volleyRequest.start();
    }

    public void onJSONObjectRequest(JSONObject argJSONObject) {
        /*Map<String, String> map = new HashMap<String, String>();
        map.put("param1", "example");
        JSONObject jsonObject = new JSONObject(map);
        System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): Response " + jsonObject.toString());*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestURL, argJSONObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): Response " + jsonObject.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        onVolleyErrorHandler(argVolleyError);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                if (headerParams == null) {
                    headerParams = new HashMap<String, String>();
                    return headerParams;
                }
                headerParams.put("Content-Type", "application/json");
                //headerParams.put("Content-Type", "application/x-www-form-urlencoded");
                Map<String, String> treeURLParameters = new TreeMap<String, String>(headerParams);
                return treeURLParameters;
            }
        };
        volleyRequest.add(jsonObjectRequest);
        volleyRequest.start();
    }

    public void onJSONArrayRequest(JSONArray argJSONArray) {
        System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): " + argJSONArray.toString());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, requestURL, argJSONArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        System.out.println("DEBUG_LOG_PRINT (HTTPVolleyRequest): onResponse " + jsonArray.toString());
                        if (eventListener != null) {
                            eventListener.onResponse(jsonArray.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError argVolleyError) {
                        onVolleyErrorHandler(argVolleyError);
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                if (headerParams == null) {
                    headerParams = new HashMap<String, String>();
                    return headerParams;
                }
                headerParams.put("Content-Type", "application/json");
                //headerParams.put("Content-Type", "application/x-www-form-urlencoded");
                Map<String, String> treeURLParameters = new TreeMap<String, String>(headerParams);
                return treeURLParameters;
            }
        };
        volleyRequest.add(jsonArrayRequest);
        volleyRequest.start();
    }

    private void onVolleyErrorHandler(VolleyError argVolleyError) {
        //LogWriter.Log("(HTTPVolleyRequest) - onErrorResponse VolleyError " + argVolleyError.toString());
        //System.out.println("DEBUG_LOG_PRINT Error Response code: " + argVolleyError.networkResponse.statusCode);
        String errorMessage = "";
        String responseCode = "";
        if (argVolleyError.networkResponse != null) {
            responseCode = argVolleyError.networkResponse.statusCode + "";
            //LogWriter.Log("(HTTPVolleyRequest) - onErrorResponse Error responseCode " + responseCode);
            try {
                errorMessage = new String(argVolleyError.networkResponse.data, "UTF-8");
                //LogWriter.Log("(HTTPVolleyRequest) - onErrorResponse Error errorMessage " + errorMessage);
            } catch (UnsupportedEncodingException e) {
                //LogWriter.Log("(HTTPVolleyRequest) - error UnsupportedEncodingException " + e.toString());
            }
        }
        if (eventListener != null) {
            eventListener.onError(argVolleyError);
            eventListener.onError(argVolleyError, responseCode, errorMessage);
        }
    }

    public interface EventListenerHandler {
        public void onResponse(String argResponse);

        public void onError(VolleyError argVolleyError);

        public void onError(VolleyError argVolleyError, String argStatusCode, String argErrorMessage);
    }

    public String getAppVersion(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            //return packageInfo.versionCode;
            //packageInfo.packageName;
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public enum HTTPMethod {
        GET(0),
        POST(1);
        private final int value;

        HTTPMethod(int argValue) {
            this.value = argValue;
        }

        public int getValue() {
            return this.value;
        }
    }
}
//https://www.itsalif.info/content/android-volley-tutorial-http-get-post-put
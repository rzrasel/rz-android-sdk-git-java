package com.rzandjavagit.raw;

import android.content.Context;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rzandjavagit.volleyhelper.CodeMarker;
import com.rzandjavagit.volleyhelper.HTTPVolleyParserHelper;
import com.rzandjavagit.volleyhelper.HTTPVolleyRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class HTTPVolleyParser {
    @CodeMarker("Variable Declaration Start")
    private Context context;
    private HTTPVolleyRequest httpVolleyRequest = null;
    private String httpRequestURL;
    private EventListenerHandler eventListener;
    private HashMap<String, String> urlParameters = null;
    private HashMap<String, String> headerParams = null;
    private HashMap<String, String> codeMark = null;
    private Class modelClass;
    private String cacheParentKey = null;
    private String cacheKey = "CacheHTTPVolleyParser";

    public HTTPVolleyParser(Context argContext) {
        //System.out.println("(HTTPVolleyParser): constructor ");
        context = argContext;
        httpVolleyRequest = new HTTPVolleyRequest(context);
    }

    public HTTPVolleyParser setEventListener(EventListenerHandler argEventListener) {
        //System.out.println("(HTTPVolleyParser): setEventListener ");
        eventListener = argEventListener;
        return this;
    }

    public HTTPVolleyParser withRUL(String argHTTPRequestURL) {
        httpRequestURL = argHTTPRequestURL;
        cacheKey = httpRequestURL;
        return this;
    }

    public HTTPVolleyParser withHeaderParameters(HashMap<String, String> argHeaderParameters) {
        headerParams = argHeaderParameters;
        return this;
    }

    public HTTPVolleyParser withURLParameters(HashMap<String, String> argURLParameters) {
        urlParameters = argURLParameters;
        return this;
    }

    public HTTPVolleyParser withModel(Class<?> argModelClass) {
        modelClass = argModelClass;
        return this;
    }

    /*private <T> Type getTypeToken(Class<T> argTypeToken) {
     *//*Type typeToken = new TypeToken<ArrayList<ModelUsual>>() {
        }.getType();*//*
        return new TypeToken<ArrayList<T>>() {
        }.getType();
    }*/

    public void onExecute(HTTPVolleyRequest.HTTPMethod argHTTPMethod) {
        /*HashMap<String, String> headerParams = new HashMap<>();
        headerParams.put("Authorization", "Token " + UserSession.getApiAuthToken());
        HTTPVolleyRequest.Method httpMethod = HTTPVolleyRequest.Method.GET;
        if (argHTTPMethod == HTTPMethod.POST) {
            httpMethod = HTTPVolleyRequest.Method.POST;
        }*/
        /*Object object = onCachingTask(cacheKey);
        if (object != null) {
            //LogWriter.Log("NOT_NULL");
            ArrayList<T> modelDataList = (ArrayList<T>) object;
            //LogWriter.Log("DEBUG_LOG_PRINT (HTTPVolleyParser): ArrayList_SIZE_ME: " + modelDataList.size());
            if (eventListener != null) {
                eventListener.onSuccess(modelDataList);
                System.out.println("DEBUG_LOG_PRINT (HTTPVolleyParser): called: eventListener.onSuccess");
            }
            return;
        }*/
        httpVolleyRequest.setURL(httpRequestURL)
                .setHeaderParameters(headerParams)
                .setURLParameters(urlParameters)
                .setEventListener(new HTTPVolleyRequest.EventListenerHandler() {
                    @Override
                    public void onResponse(String argResponse) {
                        //System.out.println("DEBUG_LOG_PRINT (HTTPVolleyParser): Response " + argResponse);
                        Gson gson = new Gson();
                        if (modelClass != null) {
                            Type typeToken = HTTPVolleyParserHelper.getTypeToken(modelClass);
                            //ArrayList<T> modelDataList = new GsonBuilder().create().fromJson(argResponse.toString(), typeToken);
                            ArrayList<Object> modelDataList = new GsonBuilder().create().fromJson(argResponse.toString(), typeToken);
                            //System.out.println("DEBUG_LOG_PRINT (HTTPVolleyParser): Response size " + modelDataList.size());
                            //onCachingTask(cacheKey, modelDataList);
                            if (eventListener != null) {
                                eventListener.onSuccess(modelDataList);
                            }
                        }
                        if (eventListener != null) {
                            eventListener.onSuccess(argResponse);
                        }
                    }

                    @Override
                    public void onError(VolleyError argVolleyError) {
                        //System.out.println("DEBUG_LOG_PRINT (HTTPVolleyParser): Error " + argVolleyError.toString());
                        //Toast.makeText(context, "Invalid request", Toast.LENGTH_LONG).show();
                        if (eventListener != null) {
                            eventListener.onError(argVolleyError);
                        }
                    }

                    @Override
                    public void onError(VolleyError argVolleyError, String argStatusCode, String argErrorMessage) {
                        //System.out.println("DEBUG_LOG_PRINT (HTTPVolleyParser): Error " + argStatusCode);
                        if (eventListener != null) {
                            eventListener.onError(argVolleyError, argStatusCode, argErrorMessage);
                        }
                    }
                })
                .onStringRequest(argHTTPMethod);
    }

    public interface EventListenerHandler {
        public void onSuccess(String argResponseData);

        public void onSuccess(ArrayList<?> argModelDataList);
        //public void onSuccess(ArrayList<Object> argModelDataList);

        public void onError(VolleyError argError);

        public void onError(VolleyError argVolleyError, String argStatusCode, String argErrorMessage);
    }
}
/*
HTTPVolleyParser httpVolleyParser = new HTTPVolleyParser(context);
httpVolleyParser.withRUL(AppConstant.Server.URLS.DISEASE)
        .withModel(ModelUsual.class)
        .setEventListener(new HTTPVolleyParser.EventListenerHandler() {
            @Override
            public void onSuccess(String argResponseData) {
                //
            }

            @Override
            public void onSuccess(ArrayList<?> argModelDataList) {
                ArrayList<ModelUsual> modelDataList = (ArrayList<ModelUsual>) argModelDataList;
                System.out.println("DEBUG_LOG_PRINT (ActDashboard): size " + modelDataList.size());
            }

            @Override
            public void onError(VolleyError argError) {
                //
            }

            @Override
            public void onError(VolleyError argVolleyError, String argStatusCode, String argErrorMessage) {
                //
            }
        })
        .onExecute(HTTPVolleyParser.HTTPMethod.GET);
*/
//https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/widget/TextView.java
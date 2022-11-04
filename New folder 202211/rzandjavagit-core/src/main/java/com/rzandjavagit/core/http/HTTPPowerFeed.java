package com.rzandjavagit.core.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HTTPPowerFeed {
    private static String methodName = "methodName";
    private String httpRequestURL;
    private final String userAgent = "Mozilla/5.0";
    private HTTPMethod httpMethod;
    private boolean isAllowRedirects = true;
    private int readTimeout = 0;
    private int connectTimeout = 0;
    private boolean isUseCaches = false;
    private HashMap<String, String> urlHeaders = new HashMap<String, String>();
    private HashMap<String, String> urlRequestParameters = new HashMap<String, String>();

    public HTTPPowerFeed onPrepareConnection(String argHTTPRequestURL, HTTPMethod argHTTPMethod, boolean argIsAllowRedirects) {
        this.httpRequestURL = argHTTPRequestURL;
        this.httpMethod = argHTTPMethod;
        this.isAllowRedirects = argIsAllowRedirects;
        return this;
    }

    public HTTPPowerFeed withHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlHeaders = argUrlHeaderList;
        return this;
    }

    public HTTPPowerFeed withParameters(HashMap<String, String> argRequestParameterList) {
        this.urlRequestParameters = argRequestParameterList;
        return this;
    }

    public HTTPPowerFeed withReadTimeout(int argReadTimeout) {
        this.readTimeout = argReadTimeout;
        return this;
    }

    public HTTPPowerFeed withConnectTimeout(int argConnectTimeout) {
        this.readTimeout = argConnectTimeout;
        return this;
    }

    public HTTPPowerFeed withCaches(boolean argIsUseCaches) {
        this.isUseCaches = argIsUseCaches;
        return this;
    }

    public HTTPPowerFeed withCharSet(String argCharSet) {
        return this;
    }

    public HTTPPowerFeed withUserAgent(String argUserAgent) {
        return this;
    }

    public HTTPPowerFeed withContentType(String argContentType) {
        return this;
    }

    public String onRun() {
        try {
            URL requestURL = new URL(httpRequestURL);
            //Get url protocol: domainURL.getProtocol()
            //LogWriter.Log("HTTP_REQUESTED_URL: " + strDomainURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) requestURL.openConnection();
            httpURLConnection.setRequestMethod(httpMethod.getMethodName());
            //LogWriter.Log("HTTP_REQUESTED_METHODS: " + httpMethod.getMethodName());
            if (connectTimeout > 0) //15000
                httpURLConnection.setConnectTimeout(connectTimeout);
            if (readTimeout > 0) //15000
                httpURLConnection.setReadTimeout(readTimeout);
            httpURLConnection.setUseCaches(isUseCaches);
            //httpURLConnection.setRequestProperty("User-Agent", userAgent);
            httpURLConnection.setInstanceFollowRedirects(isAllowRedirects);
            httpURLConnection.setRequestProperty("charset", "utf-8");
            httpURLConnection.setRequestProperty("User-Agent", "your user agent");
            //httpURLConnection.setRequestProperty("User-agent", System.getProperty("http.agent"));
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            if (urlHeaders.size() > 0) {
                //JSONObject jsonObject = new JSONObject(urlHeaderList);
                //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
                String strHeaders = "";
                for (Map.Entry<String, String> entry : urlHeaders.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    strHeaders += key + "=" + value + "&";
                    httpURLConnection.setRequestProperty(key, value);
                }
                //LogWriter.Log("HTTP_HEADER: " + strHeaders);
            }
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            if (httpMethod.POST == httpMethod && urlRequestParameters.size() > 0) {
                //onWriteHttpUrlData(httpURLConnection);
                String urlRequestParam = getFormattedURLParameters(urlRequestParameters);
                onURLWriter(httpURLConnection, urlRequestParam);
                //LogWriter.Log("HTTP_PARAMETERS:" + urlRequestParam);
            }
            int responseCode = httpURLConnection.getResponseCode();
            //LogWriter.Log("HTTP_RESPONSE_CODE: " + responseCode);
            String httpURLData = onURLReader(httpURLConnection);
            //LogWriter.Log("HTTP_DATA: " + httpURLData);
            httpURLConnection.disconnect();
            if (httpURLData != null) {
                //LogWriter.Log("URL_DATA: " + httpURLData);
            }
            return httpURLData;
        } catch (MalformedURLException e) {
            //LogWriter.Log("PRINT_ERROR_MalformedURLException: " + e.toString());
        } catch (IOException e) {
            //LogWriter.Log("PRINT_ERROR_IOException: " + e.toString());
        }
        return null;
    }

    private String getFormedURLParameters(Map<String, String> argUrlParameters) {
        //byte[] retVal;
        String retVal = null;
        StringBuilder stringBuilderUrlParams = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = argUrlParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> urlParameters = iterator.next();
            stringBuilderUrlParams.append(urlParameters.getKey()).append('=').append(urlParameters.getValue());
            if (iterator.hasNext()) {
                stringBuilderUrlParams.append('&');
            }
        }
        //String strUrlParams = stringBuilderUrlParams.toString();
        //retVal = strUrlParams.getBytes();
        retVal = stringBuilderUrlParams.toString();
        //LogWriter.Log("HTTP_REQUESTED_PARAMETERS: " + retVal);
        return retVal;
    }

    private void onURLWriter(HttpURLConnection argHttpURLConnection, String argRequestParameters) {
        String urlParams = argRequestParameters;
        try {
            OutputStream outputStream = argHttpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(urlParams);
            writer.flush();
            writer.close();
            outputStream.close();
        } catch (IOException e) {
            //LogWriter.Log("PRINT_ERROR_IOException:- " + e.getMessage().toString());
        }
    }

    private String onURLReader(HttpURLConnection argHttpURLConnection) {
        String retVal = null;
        try {
            InputStream inputStream = null;
            StringBuilder stringBuilder = null;
            if (argHttpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = argHttpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                //argHttpURLConnection.disconnect();
                retVal = stringBuilder.toString();
                //LogWriter.Log("PRINT_HTTP_DATA_STRING: " + stringBuilder.toString());
            } else {
                inputStream = argHttpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    //sb.append(line + "\n");
                    stringBuilder.append(line);
                }
                inputStream.close();
                //argHttpURLConnection.disconnect();
                //LogWriter.Log("PRINT_HTTP_DATA_STRING: " + stringBuilder.toString());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            //LogWriter.Log("PRINT_ERROR_IOException:- " + e);
        }
        return retVal;
    }

    protected static String getFormattedURLParameters(Map<String, String> argUrlParameters) {
        //byte[] retVal;
        String retVal = null;
        StringBuilder stringBuilderUrlParams = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = argUrlParameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> urlParameters = iterator.next();
            stringBuilderUrlParams.append(urlParameters.getKey()).append('=').append(urlParameters.getValue());
            if (iterator.hasNext()) {
                stringBuilderUrlParams.append('&');
            }
        }
        //String strUrlParams = stringBuilderUrlParams.toString();
        //retVal = strUrlParams.getBytes();
        retVal = stringBuilderUrlParams.toString();
        //LogWriter.Log("HTTP_REQUESTED_PARAMETERS: " + retVal);
        return retVal;
    }

    public interface OnPowerFeedListener {
        public void onPreExecute();

        public Object doInBackground(String... argURLParams);

        public void onPostExecute(Object argResult);

        public void onProgressUpdate(Integer... argProgressValue);

        public void onCancelled();
    }

    public enum HTTPMethod {
        GET("GET"),
        POST("POST"),
        DELETE("DELETE");
        private final String item;

        HTTPMethod(String argMethodName) {
            this.item = argMethodName;
        }

        public String getMethodName() {
            return this.item;
        }
    }

    private static boolean isNullOrEmpty(String argValue) {
        methodName = "isNullOrEmpty(String argValue)";
        if (argValue == null) {
            return true;
        }
        if (argValue.trim().isEmpty()) {
            return true;
        }
        if (argValue.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }
}
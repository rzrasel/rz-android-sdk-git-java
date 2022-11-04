package com.rzandjavagit.core.http;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPPowerFeedMultipart {
    private static String methodName = "methodName";
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;
    private String httpRequestURL;
    private String headerAuthorization = null;
    private String headerContentType = null;
    private HashMap<String, String> urlRequestHeaders = new HashMap<String, String>();
    private HashMap<String, String> urlRequestParameters = new HashMap<String, String>();
    private HashMap<String, File> urlRequestFiles = new HashMap<String, File>();
    private HTTPMethod httpMethod;
    private final String boundary = "*****";
    private final String crlf = "\r\n";
    private final String twoHyphens = "--";
    private HashMap<String, String> httpResponseData;

    public HTTPPowerFeedMultipart() {
        urlRequestHeaders = new HashMap<String, String>();
        urlRequestParameters = new HashMap<String, String>();
        urlRequestFiles = new HashMap<String, File>();
        httpResponseData = new HashMap<String, String>();
        httpResponseData.put("code", null);
        httpResponseData.put("response", null);
    }

    //@GuardedBy("mLock")
    public HTTPPowerFeedMultipart withHeaderAuthorization(String argUrlHeaderAuthorization) {
        this.headerAuthorization = argUrlHeaderAuthorization;
        return this;
    }

    public HTTPPowerFeedMultipart withHeaderContentType(String argUrlHeaderContentType) {
        this.headerContentType = argUrlHeaderContentType;
        return this;
    }

    public HTTPPowerFeedMultipart withHeader(HashMap<String, String> argUrlHeaderList) {
        this.urlRequestHeaders = argUrlHeaderList;
        return this;
    }

    public HTTPPowerFeedMultipart withParameters(HashMap<String, String> argRequestParameterList) {
        this.urlRequestParameters = argRequestParameterList;
        return this;
    }

    public HTTPPowerFeedMultipart withFiles(HashMap<String, String> argRequestFileList) throws HTTPPowerFeedException {
        //File Name and File Path
        for (Map.Entry<String, String> entry : argRequestFileList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            File file = new File(value);
            if (file.exists()) {
                this.urlRequestFiles.put(key, file);
            } else {
                throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.FILE_PATH_NOT_EXISTS, value));
            }
        }
        return this;
    }

    public HTTPPowerFeedMultipart onPrepare(String argHTTPRequestURL) {
        this.httpRequestURL = argHTTPRequestURL;
        this.httpMethod = HTTPMethod.POST;
        return this;
    }

    public void onStart(OnAsyncEventListener argOnAsyncEventListener) {
        new PowerAsyncTask(argOnAsyncEventListener).execute(httpRequestURL);
    }

    /*public class PowerAsyncTask extends AsyncTask<String, Integer, Object> {
        private OnAsyncEventListener onAsyncEventListener;

        public PowerAsyncTask() {
        }

        public PowerAsyncTask(OnAsyncEventListener argOnAsyncEventListener) {
            this.onAsyncEventListener = argOnAsyncEventListener;
        }

        @Override
        protected void onPreExecute() {
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onPreExecute();
            }
        }

        //https://alvinalexander.com/source-code/how-write-java-method-returns-generic-type-syntax
        @Override
        protected Object doInBackground(String... argParams) {
            *//*T retVal = null;
            if (onAsyncEventListener != null) {
                retVal = (T) onAsyncEventListener.doInBackground(argURLParams);
            }*//*
            try {
                return onRun();
                //return (T) httpResponseData;
            } catch (Exception ex) {
                HashMap<String, String> errorData = new HashMap<>();
                errorData.put("code", "-1");
                errorData.put("response", ex.getMessage());
                return errorData;
            }
            //return (T) retVal;
            //https://stackoverflow.com/questions/1739515/asynctask-and-error-handling-on-android
        }

        @Override
        protected void onPostExecute(Object argResult) {
            System.out.println("DEBUG_LOG: " + argResult.toString());
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onPostExecute(argResult);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... argProgressValue) {
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onProgressUpdate(argProgressValue);
            }
        }

        @Override
        protected void onCancelled() {
            onAsyncEventListener.onCancelled();
            super.onCancelled();
        }
        //https://android--examples.blogspot.com/2017/02/android-asynctask-cancel-example.html
    }*/
    public class PowerAsyncTask<T> extends AsyncTask<T, Integer, T> {
        private OnAsyncEventListener onAsyncEventListener;

        public PowerAsyncTask() {
        }

        public PowerAsyncTask(OnAsyncEventListener argOnAsyncEventListener) {
            this.onAsyncEventListener = argOnAsyncEventListener;
        }

        @Override
        protected void onPreExecute() {
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onPreExecute();
            }
        }

        //https://alvinalexander.com/source-code/how-write-java-method-returns-generic-type-syntax
        @Override
        protected T doInBackground(T... argURLParams) {
            T retVal = null;
            try {
                retVal = (T) onRun();
                if (onAsyncEventListener != null) {
                    retVal = (T) onAsyncEventListener.doInBackground(retVal);
                }
                return retVal;
                //return (T) httpResponseData;
            } catch (Exception ex) {
                HashMap<String, String> errorData = new HashMap<>();
                errorData.put("code", "-1");
                errorData.put("response", ex.getMessage());
                return (T) errorData;
            }
            //return (T) retVal;
            //https://stackoverflow.com/questions/1739515/asynctask-and-error-handling-on-android
            //https://alvinalexander.com/source-code/how-write-java-method-returns-generic-type-syntax
        }

        @Override
        protected void onPostExecute(T argResult) {
            System.out.println("DEBUG_LOG: " + argResult.toString());
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onPostExecute(argResult);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... argProgressValue) {
            if (onAsyncEventListener != null) {
                onAsyncEventListener.onProgressUpdate(argProgressValue);
            }
        }

        @Override
        protected void onCancelled() {
            onAsyncEventListener.onCancelled();
            super.onCancelled();
        }
        //https://android--examples.blogspot.com/2017/02/android-asynctask-cancel-example.html
    }

    public HashMap<String, String> onRun() throws HTTPPowerFeedException {
        try {
            URL requestURL = new URL(httpRequestURL);
            httpURLConnection = (HttpURLConnection) requestURL.openConnection();
            //|------------------------------------------------------------|
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true); // indicates POST method
            httpURLConnection.setDoInput(true);
            //|------------------------------------------------------------|
            if (!isNullOrEmpty(headerAuthorization)) {
                //httpURLConnection.setRequestProperty("Authorization", "Token " + UserSession.getApiAuthToken());
                httpURLConnection.setRequestProperty("Authorization", headerAuthorization);
            }
            if (!isNullOrEmpty(headerContentType)) {
                httpURLConnection.setRequestProperty("Content-Type", headerContentType);
            } else {
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            if (urlRequestHeaders.size() > 0) {
                setRequestedHeader();
            }
            //|------------------------------------------------------------|
            //httpURLConnection.setRequestProperty("User-agent", System.getProperty("http.agent"));
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + this.boundary);
            //|------------------------------------------------------------|
            httpURLConnection.setRequestMethod(HTTPMethod.POST.getMethod());
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            if (urlRequestHeaders.size() > 0) {
                //onRequestedHeader();
            }
            if (urlRequestParameters.size() > 0) {
                onRequestedParameter();
            }
            if (urlRequestFiles.size() > 0) {
                onRequestedFile();
            }
            return onFinishTask();
            //System.out.println("RESPONSE: " + response);
            //|------------------------------------------------------------|
            //|------------------------------------------------------------|
        } catch (MalformedURLException ex) {
            throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.MALFORMED_URL_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        } catch (IOException ex) {
            throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        }
    }

    private void setRequestedHeader() {
        if (urlRequestHeaders.size() > 0) {
            //JSONObject jsonObject = new JSONObject(urlHeaderList);
            //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
            //String strHeaders = "";
            for (Map.Entry<String, String> entry : urlRequestHeaders.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                //strHeaders += key + "=" + value + "&";
                httpURLConnection.setRequestProperty(key, value);
                //httpConn.setRequestProperty(name, value);
            }
            //LogWriter.Log("HTTP_HEADER: " + strHeaders);
        }
    }

    private void onRequestedHeader() throws HTTPPowerFeedException {
        if (urlRequestHeaders.size() > 0) {
            //JSONObject jsonObject = new JSONObject(urlHeaderList);
            //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
            //String strHeaders = "";
            for (Map.Entry<String, String> entry : urlRequestHeaders.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                //strHeaders += key + "=" + value + "&";
                //httpURLConnection.setRequestProperty(key, value);
                try {
                    dataOutputStream.writeBytes(this.twoHyphens + this.boundary + this.crlf);
                    dataOutputStream.writeBytes(key + ": " + value + this.crlf);
                    dataOutputStream.writeBytes(this.crlf);
                    dataOutputStream.writeBytes(value + this.crlf);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                }
                //httpConn.setRequestProperty(name, value);
            }
            //LogWriter.Log("HTTP_HEADER: " + strHeaders);
        }
    }

    private void onRequestedParameter() throws HTTPPowerFeedException {
        if (urlRequestParameters.size() > 0) {
            for (Map.Entry<String, String> entry : urlRequestParameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                try {
                    dataOutputStream.writeBytes(this.twoHyphens + this.boundary + this.crlf);
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + this.crlf);
                    dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + this.crlf);
                    dataOutputStream.writeBytes(this.crlf);
                    dataOutputStream.writeBytes(value + this.crlf);
                    dataOutputStream.flush();
                } catch (IOException ex) {
                    throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                }
            }
        }
    }

    private void onRequestedFile() throws HTTPPowerFeedException {
        if (urlRequestFiles.size() > 0) {
            for (Map.Entry<String, File> entry : urlRequestFiles.entrySet()) {
                String key = entry.getKey();
                File value = entry.getValue();
                String fileName = value.getName();
                try {
                    dataOutputStream.writeBytes(this.twoHyphens + this.boundary + this.crlf);
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                            key + "\";filename=\"" +
                            fileName + "\"" + this.crlf);
                    dataOutputStream.writeBytes(this.crlf);

                    //byte[] bytes = Files.readAllBytes(uploadFile.toPath());
                    byte[] bytes = onFileToByteArray(value.toString());
                    dataOutputStream.write(bytes);
                } catch (IOException ex) {
                    throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                }
            }
        }
    }

    private byte[] onFileToByteArray(String argFilePath) throws HTTPPowerFeedException {
        File file = new File(argFilePath);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException ex) {
            throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.FILE_NOT_FOUND_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        } catch (IOException ex) {
            throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        }
        return bytes;
    }

    private HashMap<String, String> onFinishTask() throws HTTPPowerFeedException {
        httpResponseData = new HashMap<>();
        httpResponseData.put("code", null);
        httpResponseData.put("response", null);
        String response = null;

        try {
            dataOutputStream.writeBytes(this.crlf);
            dataOutputStream.writeBytes(this.twoHyphens + this.boundary + this.twoHyphens + this.crlf);
            dataOutputStream.flush();
            dataOutputStream.close();

            // checks server's status code first
            int status = httpURLConnection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK || status != HttpURLConnection.HTTP_OK) {
                InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();
                response = stringBuilder.toString();
                httpURLConnection.disconnect();
                httpResponseData.put("code", status + "");
                httpResponseData.put("response", response);
                return httpResponseData;
            } else {
                InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
                String line = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((line = responseStreamReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                responseStreamReader.close();
                response = stringBuilder.toString();
                httpURLConnection.disconnect();
                httpResponseData.put("code", status + "");
                httpResponseData.put("response", response);
                //throw new IOException("Server returned non-OK status: " + status);
                //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.RESPONSE_ERROR, response + " - " + "Server returned non-OK status: " + status));
                return httpResponseData;
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
            httpResponseData.put("code", -1 + "");
            httpResponseData.put("response", "Error: " + ex.getMessage());
            return httpResponseData;
        }
        //return response;
    }

    public HashMap<String, String> getHttpResponse() {
        return this.httpResponseData;
    }

    private enum HTTPMethod {
        GET("GET"),
        POST("POST"),
        DELETE("DELETE");
        private final String item;

        HTTPMethod(String argMethodName) {
            this.item = argMethodName;
        }

        String getMethod() {
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

    public interface OnAsyncEventListener<T> {
        public void onPreExecute();

        public T doInBackground(T... argURLParams);

        public T doInBackground(T argValue);

        public void onPostExecute(T argResult);

        public void onProgressUpdate(Integer... argProgressValue);

        public void onCancelled();
    }
}
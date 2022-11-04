package com.rzandjavagit.prohttprequest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.rzandjavagit.prohttprequest.http.FileInfo;
import com.rzandjavagit.prohttprequest.http.Response;

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
import java.util.List;
import java.util.Map;

public class ProFileUpload {
    private ProRequestBuilder requestBuilder;
    private Method method;
    //
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;
    private final String boundary = "*****";
    private final String crlf = "\r\n";
    private final String twoHyphens = "--";
    private final int successWhatValue = 1;
    private final int errorWhatValue = 0;
    private String responseString = null;
    private String urlString = null;
    //
    private ResponseListener responseListener;

    public ProFileUpload() {
    }

    public ProFileUpload requestBuilder(ProRequestBuilder argRequestBuilder) {
        requestBuilder = argRequestBuilder;
        return this;
    }

    public ProFileUpload setResponseListener(ResponseListener argResponseListener) {
        this.responseListener = argResponseListener;
        return this;
    }

    public void build(String argUrlString, Method argMethod) {
        this.urlString = argUrlString;
        this.method = argMethod;
        this.requestBuilder.response().setIsSuccess(true);
        this.requestBuilder.response().setMessage("Successfully file uploaded");
        thread.start();
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            onStart();
            handler.sendEmptyMessage(successWhatValue);
        }
    };

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message argMessage) {
            if (argMessage.what == successWhatValue) {
                /*System.out.println("========================|SUCCESS|========================");
                System.out.println("========================|" + responseString + "|========================");
                System.out.println("========================|SUCCESS|========================");*/
                if (!requestBuilder.response().isSuccess()) {
                    requestBuilder.response().setBody(responseString);
                    responseListener.onFailure(requestBuilder.response());
                } else {
                    requestBuilder.response().setBody(responseString);
                    responseListener.onSuccess(requestBuilder.response());
                }
            } else {
                /*System.out.println("========================|ERROR|========================");
                System.out.println("========================|" + responseString + "|========================");
                System.out.println("========================|ERROR|========================");*/
                //showError();
                if (responseListener != null) {
                    responseListener.onFailure(requestBuilder.response());
                }
            }
        }
    };

    private void onStart() {
        //System.out.println("========================|START|========================");
        try {
            //|------------------------------------------------|
            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            //|------------------------------------------------|
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoOutput(true); // indicates POST method
            httpURLConnection.setDoInput(true);
            //|------------------------------------------------|
            Map<String, String> params = new HashMap<>();
            //|------------------------------------------------|
            params.put("Connection", "Keep-Alive");
            params.put("Cache-Control", "no-cache");
            params.put("Content-Type", "multipart/form-data;boundary=" + boundary);
            //|------------------------------------------------|
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                httpURLConnection.setRequestProperty(key, value);
            }
            //|------------------------------------------------|
            httpURLConnection.setRequestMethod(Method.POST.toString());
            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            /*new SetHeader();
            new SetField();
            new SetFile();*/
            //
            Map<String, String> headerParams = requestBuilder.parameter().header().rawHeader();
            for (Map.Entry<String, String> entry : headerParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                onWriteHeaderData(key, value);
            }
            //
            Map<String, String> fieldParams = requestBuilder.parameter().field().rawField();
            for (Map.Entry<String, String> entry : fieldParams.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                onWriteFieldData(key, value);
            }
            //
            List<FileInfo> fileParams = requestBuilder.parameter().file().rawFile();
            for (FileInfo item : fileParams) {
                String key = item.getKey();
                String fileName = item.getFileName();
                String filePath = item.getFilePath();
                onWriteFileData(key, fileName, filePath);
            }
            //
            onFinishedWrite();
            onReadData();
            disconnect();
            //|------------------------------------------------|
        } catch (MalformedURLException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
        }
        //System.out.println("========================|END|========================");
    }

    private void onWriteHeaderData(String argKey, String argValue) {
        httpURLConnection.setRequestProperty(argKey, argValue);
        //httpConn.setRequestProperty(name, value);
    }

    private void onWriteFieldData(String argKey, String argValue) {
        try {
            dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + argKey + "\"" + crlf);
            dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + crlf);
            dataOutputStream.writeBytes(crlf);
            dataOutputStream.writeBytes(argValue + crlf);
            //dataOutputStream.flush();
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        }
    }

    private void onWriteFileData(String argFileKey, String argFileName, String argFullFilePath) {
        try {
            dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                    argFileKey + "\";filename=\"" +
                    argFileName + "\"" + crlf);
            dataOutputStream.writeBytes(crlf);

            //byte[] bytes = Files.readAllBytes(uploadFile.toPath());
            byte[] bytes = getFileRawData(argFullFilePath);
            dataOutputStream.write(bytes);
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        }
    }

    private void onFinishedWrite() {
        try {
            dataOutputStream.writeBytes(this.crlf);
            dataOutputStream.writeBytes(this.twoHyphens + this.boundary + this.twoHyphens + this.crlf);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
        }
    }

    private void onReadData() {
        try {
            InputStream responseStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            responseStreamReader.close();
            responseString = stringBuilder.toString();
            int status = httpURLConnection.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                sendEmptyMessage(errorWhatValue);
            }
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
        }
    }

    private void disconnect() {
        httpURLConnection.disconnect();
    }

    private byte[] getFileRawData(String argFilePath) {
        File file = new File(argFilePath);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.FILE_NOT_FOUND_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        } catch (IOException e) {
            this.requestBuilder.response().setIsSuccess(false);
            this.requestBuilder.response().setMessage(e.getMessage());
            sendEmptyMessage(errorWhatValue);
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
        }
        return bytes;
    }

    private void sendEmptyMessage(int argWhat) {
        handler.sendEmptyMessage(argWhat);
    }

    public interface ResponseListener {
        void onSuccess(Response argResponse);

        void onFailure(Response argResponse);
    }
}
//https://www.techotopia.com/index.php/Android_Threads_and_Thread_Handlers_-_An_Android_Studio_Tutorial
//https://camposha.info/android-examples/android-handler/
//https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a
package com.rzandjavagit.prohttprequest;


import com.rzandjavagit.prohttprequest.http.FileInfo;

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
import java.util.List;
import java.util.Map;

public class NetHttpMultipart {
    private ProRequestBuilder requestBuilder;
    private HttpURLConnection httpURLConnection;
    private DataOutputStream dataOutputStream;
    private final String boundary = "*****";
    private final String crlf = "\r\n";
    private final String twoHyphens = "--";

    /*public NetHttpMultipart() {
    }*/

    public NetHttpMultipart requestBuilder(ProRequestBuilder argRequestBuilder, Method argMethod) {
        requestBuilder = argRequestBuilder;
        return this;
    }

    //|------------------------------------------------------------|
    public void build() {
        new SetHttpConnection();
    }

    //|------------------------------------------------------------|
    class SetHttpConnection {
        //|--------------------------------------------------------|
        public SetHttpConnection() {
            System.out.println("========================|START|========================");
            try {
                //|------------------------------------------------|
                URL url = new URL(requestBuilder.httpUrl().url);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //|------------------------------------------------|
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(true); // indicates POST method
                httpURLConnection.setDoInput(true);
                //|------------------------------------------------|
                Map<String, String> params = requestBuilder.parameter().property().rawProperty();
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
                new SetHeader();
                new SetField();
                new SetFile();
                //|------------------------------------------------|
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("========================|END|========================");
        }
        //|--------------------------------------------------------|
    }

    //|------------------------------------------------------------|
    class SetHeader {
        public SetHeader() {
            Map<String, String> params = requestBuilder.parameter().header().rawHeader();
            if (params.size() > 0) {
                //JSONObject jsonObject = new JSONObject(urlHeaderList);
                //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
                //String strHeaders = "";
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    //strHeaders += key + "=" + value + "&";
                    httpURLConnection.setRequestProperty(key, value);
                    //httpConn.setRequestProperty(name, value);
                }
                //LogWriter.Log("HTTP_HEADER: " + strHeaders);
            }
        }

        public void SetHeaderOld() {
            Map<String, String> params = requestBuilder.parameter().header().rawHeader();
            if (params.size() > 0) {
                //JSONObject jsonObject = new JSONObject(urlHeaderList);
                //LogWriter.Log("PRINT_HEADER_LIST:- " + jsonObject.toString());
                //String strHeaders = "";
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    //strHeaders += key + "=" + value + "&";
                    //httpURLConnection.setRequestProperty(key, value);
                    try {
                        dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
                        dataOutputStream.writeBytes(key + ": " + value + crlf);
                        dataOutputStream.writeBytes(crlf);
                        dataOutputStream.writeBytes(value + crlf);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                    }
                    //httpConn.setRequestProperty(name, value);
                }
                //LogWriter.Log("HTTP_HEADER: " + strHeaders);
            }
        }
    }

    //|------------------------------------------------------------|
    class SetField {
        public SetField() {
            Map<String, String> params = requestBuilder.parameter().field().rawField();
            if (params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    try {
                        dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
                        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"" + crlf);
                        dataOutputStream.writeBytes("Content-Type: text/plain; charset=UTF-8" + crlf);
                        dataOutputStream.writeBytes(crlf);
                        dataOutputStream.writeBytes(value + crlf);
                        dataOutputStream.flush();
                    } catch (IOException ex) {
                        //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                    }
                }
            }
        }
    }

    //|------------------------------------------------------------|
    class SetFile {
        public SetFile() {
            List<FileInfo> params = requestBuilder.parameter().file().rawFile();
            if (params.size() > 0) {
                for (FileInfo item : params) {
                    String key = item.getKey();
                    String fileName = item.getFileName();
                    String filePath = item.getFilePath();
                    try {
                        dataOutputStream.writeBytes(twoHyphens + boundary + crlf);
                        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                                key + "\";filename=\"" +
                                fileName + "\"" + crlf);
                        dataOutputStream.writeBytes(crlf);

                        //byte[] bytes = Files.readAllBytes(uploadFile.toPath());
                        byte[] bytes = fileToByteArray(filePath);
                        dataOutputStream.write(bytes);
                    } catch (IOException ex) {
                        //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
                    }
                }
            }
        }

        private byte[] fileToByteArray(String argFilePath) {
            File file = new File(argFilePath);
            int size = (int) file.length();
            byte[] bytes = new byte[size];
            try {
                BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
                buf.read(bytes, 0, bytes.length);
                buf.close();
            } catch (FileNotFoundException ex) {
                //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.FILE_NOT_FOUND_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
            } catch (IOException ex) {
                //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
            }
            return bytes;
        }
    }
    //|------------------------------------------------------------|
    private void  readHttp() {
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
                /*httpResponseData.put("code", status + "");
                httpResponseData.put("response", response);
                return httpResponseData;*/
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
                /*httpResponseData.put("code", status + "");
                httpResponseData.put("response", response);*/
                //throw new IOException("Server returned non-OK status: " + status);
                //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.RESPONSE_ERROR, response + " - " + "Server returned non-OK status: " + status));
                /*return httpResponseData;*/
            }
        } catch (IOException ex) {
            //ex.printStackTrace();
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.IO_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
            /*httpResponseData.put("code", -1 + "");
            httpResponseData.put("response", "Error: " + ex.getMessage());
            return httpResponseData;*/
        }
    }
    //|------------------------------------------------------------|
}

/*

Phonics iPad apps for young children 2020
https://youtu.be/MEP-IzFfX5k
Favorite Ipad Apps for Toddlers | Best Educational Apps and TV Shows for Toddlers 2020
https://youtu.be/ERoqA7RjMbg
App review Kids preschool learning app
https://youtu.be/H4hgZMfP7N0
Phonics - Learn to Read | One Hour Spelling Lesson | Alphablocks | Wizz | Cartoons for Kids
https://youtu.be/p3x5Q-h2Pu8
Monkey fix it learning android apps for kids
https://youtu.be/w119w9Z1dWY
Animal Island Learning Adventure (AILA) Preschool Learning System | Learning Session
https://youtu.be/YOFx3Nr_elA
*/

/*
software distribution folder
How to Automatically Clear Cache on Windows 10
https://youtu.be/kaIGatqglVQ
How to Clear All Cache in Windows 10
https://youtu.be/z3cjTqCQemU
#15 Retrofit Android Tutorial - User Login
https://youtu.be/j0wH0m_xYLs
Create Splash Screen in Flutter App the Right Way in 2021
https://youtu.be/JVpFNfnuOZM
*/


/*
Vector Art Effect Photoshop 2020 | Vector Illustration Effect | Vector Portrait Effect
https://youtu.be/uIs0_p2CnTM
Oil Paint Without Oil Paint Filter || PhotoShop CC 2020 || Download Action Free || Ashik Albums
https://youtu.be/mFao5vCg-6k
Cartoon Effect in Photoshop - Clone Plugin - Photoshop Tutorial
https://youtu.be/9ZpUlfudh8U
Vector Art/ Vector Portrait Cartoon Effect Photoshop Action free Download
https://youtu.be/9_bQ7ez6AR4
Uranus Photoshop Tool - Script Tool Guide
https://youtu.be/-yyYRYWBt60
New Oil Paint Action for Adobe Photoshop 2018 I 100% working zip file
https://youtu.be/5gzf_zOnTKQ
Oil Painting/ Digital Painting Photoshop Action
https://youtu.be/B7li52Lisa8
Turn Your Photo to a Sketch by Da Vinci! - Photoshop Action Review
https://youtu.be/lBIhX17hzOM
This Incredible Oil Paint Photoshop Action is 900MB!
https://www.youtube.com/watch?v=BOYdYZMd1TQ&t=471s
Adobe Photoshop CC - Tutorial FX Box & Real Paint FX Download [sub ENG], Studio72
https://www.youtube.com/watch?v=sdSxJ0qLBGQ
Oil Painting/ Digital Painting Photoshop Action
https://youtu.be/B7li52Lisa8
Cartoon Effect in Photoshop - Clone Plugin - Photoshop Tutorial
https://youtu.be/9ZpUlfudh8U
Photoshop Plugin : Cartoon Maker Clone Photoshop
https://youtu.be/ZtN-k1bHmdg
https://drive.google.com/file/d/1ta1LSaxSEILDn4kANHwd-_WvQdIDb_XY/edit

https://www.pexels.com/search/old%20man/
https://www.pexels.com/search/woman%20face/
https://unsplash.com/s/photos/old-man
*/

/*
https://www.java67.com/2020/03/top-50-java-coding-programming-problems-solutions.html
https://www.java67.com/2020/02/how-to-find-duplicate-characters-in-string-with-frequency.html
*/
/**
 * Represents a student enrolled in the school.
 * A student can be enrolled in many courses.
 */
package com.rzandjavagit.prohttprequest;


import com.rzandjavagit.prohttprequest.http.FileInfo;
import com.rzandjavagit.prohttprequest.http.Http;

import java.util.List;
import java.util.Map;

public class RequestBuilder extends Http {
    private RequestParameter requestParameter;

    public RequestBuilder() {
    }

    /*public RequestBuilder setUrl(String argUrl) {
        super.httpUrl().url = argUrl;
        return this;
    }*/

    /*public RequestBuilder setParameter(RequestParameter argRequestParameter) {
        requestParameter = argRequestParameter;
        return this;
    }*/

    public RequestBuilder setProperty(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    public RequestBuilder setProperty(Map<String, String> argParams) {
        super.parameter().property().setProperty(argParams);
        return this;
    }

    public RequestBuilder setAuthorization(String argValue) {
        this.setAuthorization("Authorization", argValue);
        return this;
    }

    public RequestBuilder setAuthorization(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    public RequestBuilder setTokenAuth(String argValue) {
        this.setAuthorization("Authorization", "Token " + argValue);
        return this;
    }

    public RequestBuilder setContentType() {
        this.setContentType("Content-Type", "application/x-www-form-urlencoded");
        return this;
    }

    public RequestBuilder setContentType(String argValue) {
        this.setContentType("Content-Type", argValue);
        return this;
    }

    public RequestBuilder setContentType(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    //|----------------------------------------------------------------|
    public RequestBuilder setFile(String argKey, String argFileName, String argFilePath) {
        super.parameter().file().setFile(argKey, argFileName, argFilePath);
        return this;
    }

    public RequestBuilder setFile(List<FileInfo> argParams) {
        super.parameter().file().setFile(argParams);
        return this;
    }
    //|----------------------------------------------------------------|
}

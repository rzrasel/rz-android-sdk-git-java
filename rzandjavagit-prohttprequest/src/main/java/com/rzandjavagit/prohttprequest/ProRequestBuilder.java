package com.rzandjavagit.prohttprequest;


import com.rzandjavagit.prohttprequest.http.FileInfo;
import com.rzandjavagit.prohttprequest.http.Http;

import java.util.List;
import java.util.Map;

public class ProRequestBuilder extends Http {
    private RequestParameter requestParameter;

    public ProRequestBuilder() {
    }

    /*public RequestBuilder setUrl(String argUrl) {
        super.httpUrl().url = argUrl;
        return this;
    }*/

    /*public RequestBuilder setParameter(RequestParameter argRequestParameter) {
        requestParameter = argRequestParameter;
        return this;
    }*/

    public ProRequestBuilder setProperty(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    public ProRequestBuilder setProperty(Map<String, String> argParams) {
        super.parameter().property().setProperty(argParams);
        return this;
    }

    public ProRequestBuilder setAuthorization(String argValue) {
        this.setAuthorization("Authorization", argValue);
        return this;
    }

    public ProRequestBuilder setAuthorization(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    public ProRequestBuilder setTokenAuth(String argValue) {
        this.setAuthorization("Authorization", "Token " + argValue);
        return this;
    }

    public ProRequestBuilder setContentType() {
        this.setContentType("Content-Type", "application/x-www-form-urlencoded");
        return this;
    }

    public ProRequestBuilder setContentType(String argValue) {
        this.setContentType("Content-Type", argValue);
        return this;
    }

    public ProRequestBuilder setContentType(String argKey, String argValue) {
        super.parameter().property().setProperty(argKey, argValue);
        return this;
    }

    //|----------------------------------------------------------------|
    public ProRequestBuilder setFile(String argKey, String argFileName, String argFilePath) {
        super.parameter().file().setFile(argKey, argFileName, argFilePath);
        return this;
    }

    public ProRequestBuilder setFile(List<FileInfo> argParams) {
        super.parameter().file().setFile(argParams);
        return this;
    }
    //|----------------------------------------------------------------|
}

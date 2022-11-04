package com.rzandjavagit.prohttprequest;


import com.rzandjavagit.prohttprequest.http.FileInfo;
import com.rzandjavagit.prohttprequest.http.Http;
import com.rzandjavagit.prohttprequest.http.Parameter;

import java.util.List;
import java.util.Map;

//public class RequestParameter extends RequestBuilder {
public class RequestParameter extends Http {
    private Parameter parameter;

    public RequestParameter() {
        /*Http http = new Http();
        this.parameter = http.parameter();
        http.setParameter(parameter);*/
        this.parameter = super.parameter();
    }

    /*public RequestParameter setUrl(String argUrl) {
        parameter.httpUrl().url = argUrl;
        return this;
    }*/

    public RequestParameter setHeader(String argKey, String argValue) {
        super.parameter().header().setHeader(argKey, argValue);
        return this;
    }

    public RequestParameter setHeader(Map<String, String> argParams) {
        super.parameter().header().setHeader(argParams);
        return this;
    }

    public RequestParameter setQuery(String argKey, String argValue) {
        super.parameter().query().setQuery(argKey, argValue);
        return this;
    }

    public RequestParameter setQuery(Map<String, String> argParams) {
        super.parameter().query().setQuery(argParams);
        return this;
    }

    public RequestParameter setField(String argKey, String argValue) {
        super.parameter().field().setField(argKey, argValue);
        return this;
    }

    public RequestParameter setField(Map<String, String> argParams) {
        super.parameter().field().setField(argParams);
        return this;
    }

    //|----------------------------------------------------------------|
    public RequestParameter setFile(String argKey, String argFileName, String argFilePath) {
        super.parameter().file().setFile(argKey, argFileName, argFilePath);
        return this;
    }

    public RequestParameter setFile(List<FileInfo> argParams) {
        super.parameter().file().setFile(argParams);
        return this;
    }
    //|----------------------------------------------------------------|
}

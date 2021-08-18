package com.rzandjavagit.prohttprequest;


public class Test {
    public NetHttpMultipart netHttpMultipart;
    public RequestBuilder requestBuilder;

    public Test() {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.setHeader("", "")
                .setQuery("", "")
                .setField("", "")
                .setFile("", "", "");
        requestBuilder = new RequestBuilder();
        requestBuilder
                //.setParameter(requestParameter)
                .setTokenAuth("token")
                .setContentType("application/x-www-form-urlencoded")
                .setProperty("Connection", "Keep-Alive")
                .setProperty("Cache-Control", "no-cache")
                .setProperty("Content-Type", "application/x-www-form-urlencoded");
        netHttpMultipart = new NetHttpMultipart();
        netHttpMultipart.requestBuilder(requestBuilder, Method.POST);
    }
}
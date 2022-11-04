package com.rzandjavagit.prohttprequest.http;

public class Http {
    private HttpUrl httpUrl;
    private Parameter parameter;
    private Response response;

    //public Parameter parameter;
    public Http() {
        //System.out.println("========================|CONSTRUCTOR_HTTP");
        httpUrl = new HttpUrl();
        parameter = new Parameter();
        response = new Response();
    }

    public HttpUrl httpUrl() {
        return httpUrl;
    }

    /*public void httpUrl(String argUrl) {
        httpUrl.url = argUrl;
    }*/

    public Parameter parameter() {
        return this.parameter;
        //return new Parameter();
    }

    /*public void setParameter(Parameter argParameter) {
        this.parameter = argParameter;
    }*/
    public Response response() {
        return this.response;
    }
}

package com.rzandjavagit.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;

public class URLBuilder {
    private C0368a f1110a;

    public URLBuilder() {
        f1110a = new C0368a();
    }

    public URLBuilder withParameter(String argParamKey, String argParamValue) {
        this.f1110a.m1391a(argParamKey, argParamValue);
        return this;
    }

    public String getURLParameter() throws UnsupportedEncodingException {
        return this.f1110a.m1392a();
    }

    public String getURLParameter(boolean argIsRemoveEmpty) throws UnsupportedEncodingException {
        return this.f1110a.m1393a(argIsRemoveEmpty);
    }

    public static String getURLParameter(HashMap<String, String> argURLRequestParameters) throws UnsupportedEncodingException {
        return C0368a.m1386a(argURLRequestParameters);
    }

    public static String getURLParameter(HashMap<String, String> argURLRequestParameters, boolean z) throws UnsupportedEncodingException {
        return C0368a.m1387a(argURLRequestParameters, z);
    }

    public static boolean isURLAlive(String argURL) throws MalformedURLException, IOException {
        return C0368a.m1388a(argURL);
    }
}

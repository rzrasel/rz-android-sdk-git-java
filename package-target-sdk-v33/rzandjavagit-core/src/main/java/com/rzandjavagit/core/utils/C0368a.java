package com.rzandjavagit.core.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <h1>C0368a</h1>
 * <p>
 * Use for object memory cache
 * </p>
 *
 * @author Rz Rasel (Md. Rashed - Uz - Zaman)
 * @version 100.00.01
 * @since 2018-12-10
 */

class C0368a {
    private TreeMap<String, String> f1108a = new TreeMap();

    C0368a() {
        this.f1108a.clear();
    }

    protected C0368a m1391a(String str, String str2) {
        this.f1108a.put(str, str2);
        return this;
    }

    protected String m1392a() throws UnsupportedEncodingException {
        if (this.f1108a == null || this.f1108a.size() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Entry entry : this.f1108a.entrySet()) {
            hashMap.put((String) entry.getKey(), (String) entry.getValue());
        }
        return C0368a.m1389b(hashMap, false);
    }

    protected String m1393a(boolean z) throws UnsupportedEncodingException {
        if (this.f1108a == null || this.f1108a.size() <= 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.f1108a);
        return C0368a.m1389b(hashMap, z);
    }

    protected static String m1386a(HashMap<String, String> hashMap) throws UnsupportedEncodingException {
        return hashMap != null ? C0368a.m1389b(hashMap, false) : null;
    }

    protected static String m1387a(HashMap<String, String> hashMap, boolean z) throws UnsupportedEncodingException {
        return hashMap != null ? C0368a.m1389b(hashMap, z) : null;
    }

    protected static String m1389b(HashMap<String, ?> hashMap, boolean z) throws UnsupportedEncodingException {
        if (hashMap == null) {
            return null;
        }
        SortedMap treeMap = new TreeMap(hashMap);
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            if (!C0368a.m1390b(str)) {
                Object value = entry.getValue();
                if (z) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(value);
                    stringBuilder2.append("");
                    if (!C0368a.m1390b(stringBuilder2.toString())) {
                        stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
                        stringBuilder.append("=");
                        stringBuilder.append(value != null ? URLEncoder.encode(value.toString(), "UTF-8") : "");
                        if (!it.hasNext()) {
                        }
                        stringBuilder.append('&');
                    }
                }
                if (!z) {
                    stringBuilder.append(URLEncoder.encode(str, "UTF-8"));
                    stringBuilder.append("=");
                    stringBuilder.append(value != null ? URLEncoder.encode(value.toString(), "UTF-8") : "");
                    if (it.hasNext()) {
                        stringBuilder.append('&');
                    }
                }
            }
        }
        String stringBuilder3 = stringBuilder.toString();
        if (stringBuilder3.endsWith("&")) {
            stringBuilder3 = stringBuilder3.substring(0, stringBuilder3.length() - 1);
        }
        return stringBuilder3;
    }

    protected static boolean m1388a(String str) throws MalformedURLException, IOException {
        try {
            URL url = new URL(str);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("HEAD");
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(30000);
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("RESPONSE_CODE: ");
            stringBuilder.append(httpURLConnection.getResponseCode());
            stringBuilder.append("");
            printStream.println(stringBuilder.toString());
            if (httpURLConnection.getResponseCode() != 200) {
                if (httpURLConnection.getResponseCode() != 200) {
                    return false;
                }
            }
            return true;
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }

    private static boolean m1390b(String str) {
        if (str == null) {
            return true;
        }
        str = str.replaceAll("\\s+", "");
        return str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }
}

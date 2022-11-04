package com.rzandjavagit.core.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * <h1>C0366a</h1>
 * <p>
 * Use for object memory cache
 * </p>
 *
 * @author Rz Rasel (Md. Rashed - Uz - Zaman)
 * @version 100.00.01
 * @since 2018-12-10
 */

class C0366a {

    public static int m1386a() {
        return ((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8;
    }

    public static boolean m1387a(String str) {
        if (str == null) {
            return true;
        }
        str = str.replaceAll("\\s+", "");
        return str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }

    public static String m1388b(String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(str.getBytes("UTF-8"), 0);
    }

    public static String m1389c(String str) throws UnsupportedEncodingException {
        return new String(Base64.decode(str, 0), "UTF-8");
    }
}

package com.rzandjavagit.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    private static String methodName = "methodName-var";
    public static boolean isNullOrEmpty(String argValue) {
        methodName = "boolean isNullOrEmpty(String argValue)";
        return CoreUtils.isNullOrEmpty(argValue);
    }

    public static int getCacheSize() {
        methodName = "int getCacheSize()";
        return CoreUtils.getCacheSize();
    }

    public static String getBase64Encode(String argString) throws UnsupportedEncodingException {
        methodName = "String getBase64Encode(String argString) throws UnsupportedEncodingException";
        return CoreUtils.getBase64Encode(argString);
    }

    public static String getBase64Decode(String argString) throws UnsupportedEncodingException {
        methodName = "String getBase64Decode(String argString) throws UnsupportedEncodingException";
        return CoreUtils.getBase64Decode(argString);
    }
}
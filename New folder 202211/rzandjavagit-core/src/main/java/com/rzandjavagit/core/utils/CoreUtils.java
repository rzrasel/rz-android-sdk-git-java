package com.rzandjavagit.core.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

class CoreUtils {
    private static String methodName = "methodName-var";

    public static boolean isNullOrEmpty(String argValue) {
        methodName = "boolean isNullOrEmpty(String argValue)";
        if (argValue == null) {
            return true;
        }
        argValue = argValue.replaceAll("\\s+", "");
        if (argValue.trim().isEmpty()) {
            return true;
        }
        if (argValue.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static void printDigits(int argNum) {
        methodName = "void printDigits(int argNum)";
        /*if (argNum / 10 > 0) {
            printDigits(argNum / 10);
        }*/
        //System.out.printf("DEBUG_LOG_PRINT: number %d ", argNum % 10);
        //System.out.println("DEBUG_LOG_PRINT: number " + argNum % 10);
        if (argNum / 10 > 0) {
            printDigits(argNum / 10);
        }
        System.out.println("DEBUG_LOG_PRINT: number " + argNum % 10);
        /*if (argNum > 0) {
            System.out.println("DEBUG_LOG_PRINT: number " + argNum % 10);
            printDigits(argNum / 10);
        }*/
    }

    public static int getCacheSize() {
        methodName = "int getCacheSize()";
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // use 1/8th of the available memory for this memory cache.
        return maxMemory / 8;
    }

    public static String getBase64Encode(String argString) throws UnsupportedEncodingException {
        methodName = "String getBase64Encode(String argString) throws UnsupportedEncodingException";
        byte[] byteArray = argString.getBytes("UTF-8");
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static String getBase64Decode(String argString) throws UnsupportedEncodingException {
        methodName = "String getBase64Decode(String argString) throws UnsupportedEncodingException";
        byte[] byteArray = Base64.decode(argString, Base64.DEFAULT);
        return new String(byteArray, "UTF-8");
    }

    /*public static boolean isNullOrEmpty(String argValue) {
        if (argValue == null) {
            return true;
        }
        argValue = argValue.replaceAll("\\s+", "");
        if (argValue.trim().isEmpty()) {
            return true;
        }
        if (argValue.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }*/
}

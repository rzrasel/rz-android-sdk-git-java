package com.rzandjavagit.core.imagepicker;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class DirectoryPathManager {
    //DirectoryPathManager
    private Context context;
    private String fullAbsolutePath;
    private String rootDirectory;
    private String packageName = "";
    private String directory;
    private boolean isPrefixPackageName = true;

    public DirectoryPathManager(Context argContext) {
        context = argContext;
    }

    public DirectoryPathManager withPackage() {
        packageName = context.getPackageName();
        return this;
    }

    public DirectoryPathManager withPackage(boolean agrHaveDot) {
        packageName = context.getPackageName();
        if (agrHaveDot) {
            packageName = "." + context.getPackageName();
        }
        return this;
    }

    public DirectoryPathManager withDirectory(String argDirectoryName) {
        if (!isNullOrEmpty(argDirectoryName)) {
            directory = removeAllStartingDots(argDirectoryName);
        }
        return this;
    }

    public DirectoryPathManager isPostfixPackageName() {
        isPrefixPackageName = false;
        return this;
    }

    public String getSystemDirectory() {
        fullAbsolutePath = getSysRootDir();
        File file = null;
        if (isPrefixPackageName) {
            if (!isNullOrEmpty(packageName)) {
                file = new File(fullAbsolutePath, packageName);
                fullAbsolutePath = file.toString();
            }
            if (!isNullOrEmpty(directory)) {
                file = new File(fullAbsolutePath, directory);
                fullAbsolutePath = file.toString();
            }
        } else {
            if (!isNullOrEmpty(directory)) {
                file = new File(fullAbsolutePath, directory);
                fullAbsolutePath = file.toString();
            }
            if (!isNullOrEmpty(packageName)) {
                file = new File(fullAbsolutePath, packageName);
                fullAbsolutePath = file.toString();
            }
        }
        return fullAbsolutePath;
    }

    public String getCacheDirectory() {
        fullAbsolutePath = getCacheRootDir();
        File file = null;
        String tempPackageName;
        String tempDirectory;
        String needle = ".";
        int needleSize = needle.length();
        if (!isNullOrEmpty(directory)) {
            tempDirectory = directory.startsWith(needle) ? directory.substring(needleSize) : directory;
            file = new File(fullAbsolutePath, tempDirectory);
            fullAbsolutePath = file.toString();
        }
        /*if (isPrefixPackageName) {
            if (!isNullOrEmpty(packageName)) {
                //file = new File(fullAbsolutePath, packageName.replaceFirst("\\.", ""));
                tempPackageName = packageName.startsWith(needle) ? packageName.substring(needleSize) : packageName;
                file = new File(fullAbsolutePath, tempPackageName);
                fullAbsolutePath = file.toString();
            }
            if (!isNullOrEmpty(directory)) {
                tempDirectory = directory.startsWith(needle) ? directory.substring(needleSize) : directory;
                file = new File(fullAbsolutePath, tempDirectory);
                fullAbsolutePath = file.toString();
            }
        } else {
            if (!isNullOrEmpty(directory)) {
                tempDirectory = directory.startsWith(needle) ? directory.substring(needleSize) : directory;
                file = new File(fullAbsolutePath, tempDirectory);
                fullAbsolutePath = file.toString();
            }
            if (!isNullOrEmpty(packageName)) {
                tempPackageName = packageName.startsWith(needle) ? packageName.substring(needleSize) : packageName;
                file = new File(fullAbsolutePath, tempPackageName);
                fullAbsolutePath = file.toString();
            }
        }*/
        return fullAbsolutePath;
    }

    private String getSysRootDir() {
        return Environment.getExternalStorageDirectory() + "";
    }

    private String getCacheRootDir() {
        return context.getCacheDir() + "";
    }

    //|------------------------------------------------------------|
    private String removeAllStartingDots(String argValue) {
        String tempDirectory;
        String needle = ".";
        int needleSize = needle.length();
        String retValue;
        retValue = argValue.trim();
        if (retValue.startsWith(needle)) {
            return removeAllStartingDots(retValue.substring(needleSize));
        } else {
            return retValue;
        }
        //return retValue;
    }

    //|------------------------------------------------------------|
    private static boolean isNullOrEmpty(String argValue) {
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

    //|------------------------------------------------------------|
    private void log(String argMessage) {
        System.out.println("PathManager_DEBUG_LOG_PRINT: " + argMessage);
    }

    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
}
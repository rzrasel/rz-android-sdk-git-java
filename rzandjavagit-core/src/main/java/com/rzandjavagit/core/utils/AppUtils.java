package com.rzandjavagit.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.StrictMode;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.rzandjavagit.core.BuildConfig;
import com.rzandjavagit.core.log.LogWriter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Rz Rasel on 2016/09/16.
 */
public class AppUtils {
    public static String getAppVersion(Context argContext) {
        PackageManager manager = argContext.getPackageManager();
        try {
            //PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            PackageInfo packageInfo = manager.getPackageInfo(argContext.getPackageName(), PackageManager.GET_META_DATA);
            //return packageInfo.versionCode;
            //packageInfo.packageName;
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static void logDebug(String argTag, String argMessage) {
        String TAG = "";
        TAG = argTag.length() > 23 ? argTag.substring(0, 23).toUpperCase() : argTag.toUpperCase();
        Log.d(TAG, argMessage);
    }

    public static Point getDisplaySize(Context argContext) {
        WindowManager windowManager = (WindowManager) argContext.getSystemService(Context.WINDOW_SERVICE);
        //Display display = getWindowManager().getDefaultDisplay();
        Display display = windowManager.getDefaultDisplay();
        /*int width = display.getWidth();
        int height = display.getHeight();*/
        Point displaySize = new Point();
        display.getSize(displaySize);
        int width = displaySize.x;
        int height = displaySize.y;
        return displaySize;
    }

    //|----|------------------------------------------------------------|
    public static void strictModeThreadPolicy() {
        //|----|------------------------------------------------------------|
        if (BuildConfig.DEBUG) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build();
            StrictMode.setThreadPolicy(policy);
        }
        //|----|------------------------------------------------------------|
    }

    //|----|------------------------------------------------------------|
    public static boolean isURLAvailable(String argURL) {
        try {
            URL resourceUrl = new URL(argURL);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) resourceUrl.openConnection();
            conn.setRequestMethod("HEAD");
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            //conn.setInstanceFollowRedirects(false);
            //conn.connect();
            LogWriter.Log("RESPONSE_CODE: " + conn.getResponseCode() + "");
            if (conn.getResponseCode() == 200) {
                //System.out.println("Server exists");
                return true;
            } else {
                //System.out.println("Server not exists");
            }
        } catch (MalformedURLException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
            LogWriter.Log("Server not exists MalformedURLException");
        } catch (IOException ex) {
            //Logger.getLogger(JDBCMain.class.getName()).log(Level.SEVERE, null, ex);
            LogWriter.Log("Server not exists IOException");
        }
        return false;
    }
    //|----|------------------------------------------------------------|
}
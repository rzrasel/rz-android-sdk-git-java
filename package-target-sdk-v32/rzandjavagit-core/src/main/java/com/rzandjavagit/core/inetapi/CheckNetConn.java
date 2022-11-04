package com.rzandjavagit.core.inetapi;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.content.ContextCompat;

/**
 * Created by Rz Rasel on 2018-01-15.
 */

public class CheckNetConn {
    private static CheckNetConn instance = null;

    public static CheckNetConn getInstance() {
        if (instance == null) {
            instance = new CheckNetConn();
        }
        return instance;
    }

    public static boolean isConnected(Context argContext) {
        int hasPermission = 0;
        hasPermission = ContextCompat.checkSelfPermission((Activity) argContext, Manifest.permission.INTERNET);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            //LogWriter.Log("Check it", "Please set the permission INTERNET");
            return false;
        }
        hasPermission = ContextCompat.checkSelfPermission((Activity) argContext, Manifest.permission.ACCESS_NETWORK_STATE);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            //LogWriter.Log("Check it", "Please set the permission ACCESS_NETWORK_STATE");
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
            if (networkInfos != null) {
                for (int i = 0; i < networkInfos.length; i++) {
                    if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}

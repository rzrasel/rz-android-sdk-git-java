package com.rzandjavagit.prointernet;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.content.ContextCompat;

public class ProInternet {
    private static ProInternet instance = null;

    public static ProInternet getInstance() {
        if (instance == null) {
            instance = new ProInternet();
        }
        return instance;
    }

    public static boolean hasConnected(Context argContext) {
        return getInstance().isConnected(argContext);
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

    private boolean isConnectedOld(Context argContext) {
        //if (Build.VERSION.SDK_INT >= 23)
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
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null) {
                return false;
            }
            /*int type = networkInfo.getType();
            String typeName = networkInfo.getTypeName();*/
            boolean connected = networkInfo.isConnected();
            if (connected)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //return false;
        /*try {
            ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected())
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;*/

        /*ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        //Toast.makeText(mContext,mContext.getString(R.string.please_connect_to_internet),Toast.LENGTH_SHORT).show();
        return false;*/
    }

    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

    /**
     * Checking for all possible internet providers
     **/
    private boolean isConnectingToInternet(Context argContext) {
        ConnectivityManager connectivity = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }
    // |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|

    private boolean checkInternetConnection_temp(Context argContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) argContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if ((connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
                || (connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState() == NetworkInfo.State.CONNECTED)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isConnected_01(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private boolean isOnline() {
        /*final ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        int type = networkInfo.getType();
        String typeName = networkInfo.getTypeName();
        boolean connected = networkInfo.isConnected();
        if (connected)
            return true;
        else
            return false;*/
        return false;
    }
}
//<uses-permission android:name="android.permission.INTERNET" />
//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
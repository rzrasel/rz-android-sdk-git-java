package com.rzandjavagit.core.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Rz Rasel on 2017-12-11.
 */

public class ManifestPermission {
    private Context context;
    private static ManifestPermission instance = null;
    private OnEventListenerHandler onEventListenerHandler;

    public static ManifestPermission getInstance(Context argContext) {
        if (instance == null) {
            instance = new ManifestPermission(argContext);
        }
        return instance;
    }

    public ManifestPermission(Context argContext) {
        context = argContext;
    }

    public boolean havePermission(String[] argPermissions) {
        /*String permission = "android.permission.WRITE_EXTERNAL_STORAGE";
        int res = getContext().checkCallingOrSelfPermission(permission);
        return (res == PackageManager.PERMISSION_GRANTED);*/
        boolean hasPermission = true;
        /*for (int i = 0; i < argPermissionSet.length; i++) {
            int permission = 0;
            //Manifest.permission.INTERNET
            permission = ContextCompat.checkSelfPermission((Activity) argContext, argPermissionSet[i]);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                LogWriter.Log("NEED_PERMISSION: " + argPermissionSet[i] + " - PERMISSION_NEED_TO_SET");
                hasPermission = false;
            }
        }*/
        for (String permissionItem : argPermissions) {
            int permission = 0;
            //Manifest.permission.INTERNET
            permission = ContextCompat.checkSelfPermission((Activity) context, permissionItem);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //LogWriter.Log("NEED_PERMISSION: " + permissionItem + " - PERMISSION_NEED_TO_SET");
                hasPermission = false;
            }
        }
        return hasPermission;
    }

    public void onRequest(String argPermissions[], int argPermissionRequestCode) {
        //((Activity) context).requestPermissions(argPermissions, argPermissionRequestCode);
        ActivityCompat.requestPermissions((Activity) context, argPermissions, argPermissionRequestCode);
        //LogWriter.Log("PERISSION_REQUEST_CODE: " + argPermissionRequestCode + " REQUEST_STRINGS: " + argPermissions.toString());
    }

    public void onSetEventListener(OnEventListenerHandler argOnEventListenerHandler) {
        onEventListenerHandler = argOnEventListenerHandler;
    }

    public void onRequestPermissionsResult(int argRequestCode, String argPermissions[], int[] argGrantResults, int argPermissionRequestCode) {
        //if(Build.VERSION.SDK_INT==Build.VERSION_CODES.M)
        //if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        //LogWriter.Log("REQUEST_CODE: " + argRequestCode + " PERISSION_REQUEST_CODE: " + argPermissionRequestCode + " REQUEST_STRINGS: " + argPermissions.toString());
        if (argRequestCode == argPermissionRequestCode) {
            if (argGrantResults.length > 0 && argGrantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! do the
                // calendar task you need to do.
                if (onEventListenerHandler != null) {
                    onEventListenerHandler.onPermissionGranted(true);
                }
            } else {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                if (onEventListenerHandler != null) {
                    onEventListenerHandler.onPermissionGranted(false);
                }
            }
            return;
        }
        // other 'switch' lines to check for other
        // permissions this app might request
    }

    public interface OnEventListenerHandler {
        public void onPermissionGranted(boolean argIsGranted);
        //public void onPermissionForbidden();
    }
}
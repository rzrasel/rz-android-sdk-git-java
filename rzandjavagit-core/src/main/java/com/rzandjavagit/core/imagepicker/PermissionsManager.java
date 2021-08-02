package com.rzandjavagit.core.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

public class PermissionsManager {
    public static boolean hasPermissions(Context argContext, String... argPermissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && argContext != null && argPermissions != null) {
            for (String permission : argPermissions) {
                if (ActivityCompat.checkSelfPermission(argContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        } else if (argContext != null && argPermissions != null) {
            for (String permission : argPermissions) {
                if (ActivityCompat.checkSelfPermission(argContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void requestPermissions(Context argContext, int argRequestCode, String... argPermissions) {
        ActivityCompat.requestPermissions((Activity) argContext, argPermissions, argRequestCode);
    }
}
/*
String[] PERMISSIONS = {
    android.Manifest.permission.READ_EXTERNAL_STORAGE,
    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
};
*/
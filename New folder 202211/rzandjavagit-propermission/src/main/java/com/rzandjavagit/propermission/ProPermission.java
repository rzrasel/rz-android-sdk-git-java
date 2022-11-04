package com.rzandjavagit.propermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProPermission {
    private final Activity activity;
    private final OnPermissionListener onPermissionListener;
    private int requestCode = -1;
    private final boolean isDebug;

    private ProPermission(Builder argBuilder) {
        this.activity = argBuilder.activity;
        this.onPermissionListener = argBuilder.listener;
        isDebug = argBuilder.isDebug;
    }

    public void onRequestPermissionsResult(String[] argPermissions, int[] argGrantResults) {
        List<String> grantedPermissions = new ArrayList<>();
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < argGrantResults.length; i++) {
            if (argGrantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(argPermissions[i]);
            } else {
                grantedPermissions.add(argPermissions[i]);
            }
        }
        onLogPrint("Granted: " + grantedPermissions.size()
                + " Denied: " + deniedPermissions.size()
                + " Total: " + argPermissions.length);
        if (grantedPermissions.size() == argPermissions.length) {
            onPermissionListener.onAllPermissionsGranted(Arrays.asList(argPermissions));
        } else {
            if (deniedPermissions.size() < argPermissions.length) {
                onPermissionListener.onPermissionsGranted(grantedPermissions);
            }
            onPermissionListener.onPermissionsDenied(deniedPermissions);
        }
    }

    public void request(int argRequestCode, String... argPermissions) {
        requestCode = argRequestCode;
        List<String> permissionNeeded = new ArrayList<>();
        for (String permission : argPermissions) {
            if (!hasPermission(permission)) {
                /*if(ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    permissionNeeded.add(permission);
                }*/
                permissionNeeded.add(permission);
            }
        }
        if (permissionNeeded.size() > 0) {
            onLogPrint("Size of permission needed: " + permissionNeeded.size());
            ActivityCompat.requestPermissions(activity, permissionNeeded.toArray(new String[permissionNeeded.size()]), requestCode);
        }
    }

    public boolean hasPermission(String... argPermissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onLogPrint("Less than marshmello");
            return true;
        }
        for (String permission : argPermissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                onLogPrint("Permission needed: " + permission);
                return false;
            }
        }
        return true;
    }

    public boolean hasRationalePermission(String... argPermissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onLogPrint("Less than marshmello");
            return false;
        }
        List<String> rationalePermissions = new ArrayList<>();
        List<String> restPermissions = new ArrayList<>();
        for (String permission : argPermissions) {
            //boolean isRationale = activity.shouldShowRequestPermissionRationale(permission);
            boolean isRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            if (isRationale) {
                rationalePermissions.add(permission);
            } else {
                restPermissions.add(permission);
            }
        }
        onLogPrint("DEBUG_LOG_PRINT>hasRationalePermission>ALL: " + restPermissions.toString());
        onLogPrint("DEBUG_LOG_PRINT>hasRationalePermission: " + rationalePermissions.toString());
        if (rationalePermissions.size() > 0) {
            return true;
        }
        return false;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public static class Builder {
        private Activity activity;
        private OnPermissionListener listener;
        private boolean isDebug = true;

        public Builder with(Activity argActivity) {
            this.activity = argActivity;
            return this;
        }

        public Builder setListener(OnPermissionListener argListener) {
            this.listener = argListener;
            return this;
        }

        public Builder isDebug(boolean argIsDebug) {
            isDebug = argIsDebug;
            return this;
        }

        public ProPermission build() {
            return new ProPermission(this);
        }
    }

    private void showToast(String argMessage) {
        if (isDebug) {
            Toast.makeText(activity, argMessage, Toast.LENGTH_LONG).show();
        }
    }

    private void onLogPrint(String argMessage) {
        int stackIndex = 3;
        if (isDebug) {
            System.out.println("DEBUG_LOG_PRINT: " + argMessage
                    + " Class: " + Thread.currentThread().getStackTrace()[stackIndex].getClassName()
                    + " Method: " + Thread.currentThread().getStackTrace()[stackIndex].getMethodName()
                    + " Line: " + Thread.currentThread().getStackTrace()[stackIndex].getLineNumber());
        }
    }
}
/*
ProPermission proPermission;
proPermission = new ProPermission.Builder()
.with(activity)
.isDebug(true)
.listener(new OnPermissionListener() {
    @Override
    public void onAllPermissionsGranted(List<String> argPermissions) {
    }

    @Override
    public void onPermissionsGranted(List<String> argPermissions) {
    }

    @Override
    public void onPermissionsDenied(List<String> argPermissions) {
    }
}).build();
*/
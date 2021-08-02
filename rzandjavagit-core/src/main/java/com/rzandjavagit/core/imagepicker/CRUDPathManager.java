package com.rzandjavagit.core.imagepicker;

import android.content.Context;

import com.rzandjavagit.core.exception.CoreError;
import com.rzandjavagit.core.exception.CoreException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CRUDPathManager {
    public static boolean isDebug = true;

    public static void onCreateDirectories(Context argContext, String argDirectoryPath) throws CoreException {
        if (!PermissionsManager.hasPermissions(argContext, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"})) {
            //throw new HTTPPowerFeedException(new ErrorReason(HTTPPowerFeedException.ErrorDescription.FILE_NOT_FOUND_EXCEPTION, ex.getMessage() + " - " + httpRequestURL));
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.PERMISSIONS_DENIED, "Need permission: " + "android.permission.WRITE_EXTERNAL_STORAGE"));
        }
        File file = new File(argDirectoryPath);
        if (!isDirExists(file)) {
            file.mkdirs();
            log("Directory created: " + argDirectoryPath);
        } else {
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.DIRECTORY_ALREADY_EXISTS, argDirectoryPath + " already exists"));
        }
    }

    public static boolean isDirExists(String argFile) {
        File file = new File(argFile);
        return (file.exists() && file.isDirectory());
    }

    public static boolean isDirExists(File argFile) {
        return (argFile.exists() && argFile.isDirectory());
    }

    public static boolean isFileExists(String argFile) {
        File file = new File(argFile);
        return file.exists() && file.isFile();
    }

    public static boolean isFileExists(File argFile) {
        return argFile.exists() && argFile.isFile();
    }

    public static void onFileCopy(String argSourcePath, String argDestinationPath) throws CoreException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            file = new File(argSourcePath);
            inputStream = new FileInputStream(file);
            file = new File(argDestinationPath);
            outputStream = new FileOutputStream(file);
            byte[] byteArray = new byte[1024];
            int length;
            while ((length = inputStream.read(byteArray)) > 0) {
                outputStream.write(byteArray, 0, length);
            }
        } catch (FileNotFoundException ex) {
            //e.printStackTrace();
            //log("Error: " + e.getMessage());
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.FILE_NOT_FOUND_EXCEPTION, ex.getMessage()));
        } catch (IOException ex) {
            //e.printStackTrace();
            //log("Error: " + e.getMessage());
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.IO_EXCEPTION, ex.getMessage()));
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException ex) {
                //e.printStackTrace();
                //log("Error: " + e.getMessage());
                throw new CoreException(new CoreError().setReason(CoreError.TYPE.IO_EXCEPTION, ex.getMessage()));
            }
        }
    }

    public static void onFileCopy(File argSourcePath, File argDestinationPath) throws CoreException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(argSourcePath);
            outputStream = new FileOutputStream(argDestinationPath);
            byte[] byteArray = new byte[1024];
            int length;
            while ((length = inputStream.read(byteArray)) > 0) {
                outputStream.write(byteArray, 0, length);
            }
        } catch (FileNotFoundException ex) {
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.FILE_NOT_FOUND_EXCEPTION, ex.getMessage()));
        } catch (IOException ex) {
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.IO_EXCEPTION, ex.getMessage()));
        } finally {
            try {
                outputStream.close();
                inputStream.close();
            } catch (IOException ex) {
                //e.printStackTrace();
                throw new CoreException(new CoreError().setReason(CoreError.TYPE.IO_EXCEPTION, ex.getMessage()));
            }
        }
    }

    public static boolean deleteFile(Context argContext, String argFilePath) throws CoreException {
        if (!PermissionsManager.hasPermissions(argContext, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"})) {
            throw new CoreException(new CoreError().setReason(CoreError.TYPE.PERMISSIONS_DENIED, "Need permission android.permission.WRITE_EXTERNAL_STORAGE"));
        }
        File file = new File(argFilePath);
        if (file.exists()) {
            file.delete();
        } else {
            //throw new CoreException(new CoreError().setReason(CoreError.TYPE.FILE_NOT_FOUND_EXCEPTION, argFilePath + " not found"));
        }
        return file.exists();
    }

    private static void log(String argMessage) {
        if (isDebug) {
            System.out.println("CRUDPathManager_DEBUG_LOG_PRINT: " + argMessage);
        }
        boolean installedMaps = false;

        /*// CHECK IF AN APPLICATION IS INSTALLED
        PackageManager pkManager = getPackageManager();
        try {
            PackageInfo pkInfo = pkManager.getPackageInfo("com.google.android.apps.maps", 0); // REPLACE THIS "com.google.android.apps.maps" WITH THE ACTUAL PACAKAGE NAME
            // Log.e("pkInfo", pkInfo.toString());
            installedMaps = true;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            installedMaps = false;
        }*/
    }
}
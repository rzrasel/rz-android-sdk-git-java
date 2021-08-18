package com.rzandjavagit.proexternalstorage;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class ProExternalStorage {
    /*//checks if external storage is available for read and write
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }*/

    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    // Read only / Readable Storage
    //checks if external storage is available for read
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static ProExternalStorage getThis() {
        return new ProExternalStorage();
    }

    public static boolean isDirWriteable(String argDirectoryPath) {
        File sdFile = new File(argDirectoryPath);
        /*if (sdFile.exists()) {
            if (sdFile.isDirectory() && sdFile.canWrite()) {
                return true;
            }
        }*/
        if (sdFile.isDirectory() && sdFile.canWrite()) {
            return true;
        }
        return false;
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getWriteableDirectory(Context argContext, DirectoryType argDirectoryType, String argDirectoryName) {
        return getWriteableDirectory(argContext, argDirectoryType, argDirectoryName, false);
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getWriteableDirectory(Context argContext, DirectoryType argDirectoryType, String argDirectoryName, boolean argIsPublic) {
        if (argIsPublic) {
            if (argDirectoryType == null) {
                argDirectoryType = DirectoryType.DIRECTORY_DOCUMENTS;
            } else {
                if (argDirectoryType == DirectoryType.NULL || argDirectoryType == DirectoryType.NONE) {
                    argDirectoryType = DirectoryType.DIRECTORY_DOCUMENTS;
                }
            }
            return getPublicDirPath(argDirectoryType, argDirectoryName);
        }
        String directoryPath = null;
        if (isExternalStorageAvailable() || isExternalStorageReadable()) {
            directoryPath = getDirPath();
            if (argContext != null) {
                directoryPath = getDirPath(argContext);
            } else if (argDirectoryName != null) {
                directoryPath = getDirPath(argDirectoryName);
            }
            if (!isDirWriteable(directoryPath)) {
                directoryPath = getPublicDirPath(argContext, argDirectoryType, argDirectoryName);
            }
            if (!isDirWriteable(directoryPath)) {
                directoryPath = getPublicDirPath(argDirectoryType, argDirectoryName);
            }
        }
        return directoryPath;
    }

    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String getPublicDirPath(DirectoryType argDirectoryType, String argDirectoryName) {
        //String directoryPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        //String directoryPath = Environment.getExternalStoragePublicDirectory("Documents").getAbsolutePath();
        String directoryPath = Environment.getExternalStoragePublicDirectory(argDirectoryType.toString()).getAbsolutePath();
        File sdFile = new File(directoryPath, argDirectoryName);
        return getThis().onDirectoryPath(sdFile.getAbsolutePath());
    }

    public static String getPublicDirPath(Context argContext, String argDirectoryName) {
        return getThis().getPublicDirPath(argContext, DirectoryType.NULL, argDirectoryName);
    }

    public static String getPublicDirPath(Context argContext, DirectoryType argDirectoryType, String argDirectoryName) {
        String directoryType = null;
        if (argDirectoryType != null) {
            if (argDirectoryType != DirectoryType.NULL && argDirectoryType != DirectoryType.NONE) {
                directoryType = argDirectoryType.toString();
            }
        }
        String directoryPath = argContext.getExternalFilesDir(directoryType).getAbsolutePath();
        File sdFile = new File(directoryPath);
        if (argDirectoryName != null) {
            sdFile = new File(directoryPath + File.separator + argDirectoryName);
        }
        return getThis().onDirectoryPath(sdFile.getAbsolutePath());
    }

    public static String getDirPath() {
        return getThis().onDirectoryEnvPath(null, null);
    }

    public static String getDirPath(String argDirectoryName) {
        return getThis().onDirectoryEnvPath(null, argDirectoryName);
    }

    public static String getDirPath(Context argContext) {
        return getThis().onDirectoryEnvPath(argContext, null);
    }

    /*public static String getStorageDir(String argDirectoryName, String argFileName) {
        return getThis().onDirectory(null, argDirectoryName, argFileName);
    }*/
    public static String getBaseDir(Context argContext) {
        String baseDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        } else {
            baseDir = "/data/data/" + argContext.getApplicationContext().getPackageName() + "/";
        }

        return baseDir;
    }

    private String onDirectoryEnvPath(Context argContext, String argDirectoryName) {
        String directoryName = "";
        if (argContext != null) {
            directoryName = argContext.getPackageName();
        } else if (argDirectoryName != null) {
            directoryName = argDirectoryName;
        }
        //create folder
        File file = new File(Environment.getExternalStorageDirectory(), directoryName);
        /*if (directoryName != null) {
            file = new File(Environment.getExternalStorageDirectory(), directoryName);
        }*/
        if (!file.exists()) {
            if (!file.mkdirs()) {
                file.mkdirs();
            }
        }
        //String filePath = file.getAbsolutePath() + File.separator + argFileName;
        String filePath = file.getAbsolutePath();
        return filePath;
    }

    private String onDirectoryPath(String argDirectoryName) {
        String directoryName = "";
        if (argDirectoryName != null) {
            directoryName = argDirectoryName;
        }
        //create folder
        File file = new File(directoryName);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                file.mkdirs();
            }
        }
        //String filePath = file.getAbsolutePath() + File.separator + argFileName;
        String filePath = file.getAbsolutePath();
        return filePath;
    }

    public static boolean onDelete(String argPathLocation) {
        File sdFile = new File(argPathLocation);
        if (sdFile.exists()) {
            return sdFile.delete();
        }
        return false;
    }

    public static boolean onForceDelete(String argPathLocation) {
        File sdDir = new File(argPathLocation);
        if (sdDir.isDirectory()) {
            String[] children = sdDir.list();
            //File[] children = sdDir.listFiles();
            for (int i = 0; i < children.length; i++) {
                //new File(sdDir, children[i]).delete();
                onDeleteFile(sdDir.getAbsolutePath(), children[i]);
            }
            return onDelete(sdDir.getAbsolutePath());
        }
        return false;
    }

    public static boolean onDeleteFile(String argDirPath, String argFileName) {
        File sdFile = new File(argDirPath, argFileName);
        return onDeleteFile(sdFile.getAbsolutePath());
    }

    public static boolean onDeleteFile(String argFilePath) {
        File sdFile = new File(argFilePath);
        if (sdFile.exists()) {
            if (sdFile.isFile() && !sdFile.isDirectory()) {
                return sdFile.delete();
            }
        }
        return false;
    }

    //
    //
    //
    //
    /*public static String DIRECTORY_ALARMS = "Alarms";
    public static String DIRECTORY_AUDIOBOOKS = "Audiobooks";
    public static String DIRECTORY_DCIM = "DCIM";
    public static String DIRECTORY_DOCUMENTS = "Documents";
    public static String DIRECTORY_DOWNLOADS = "Download";
    public static String DIRECTORY_MOVIES = "Movies";
    public static String DIRECTORY_NOTIFICATIONS = "Notifications";
    public static String DIRECTORY_PICTURES = "Pictures";
    public static String DIRECTORY_PODCASTS = "Podcasts";
    public static String DIRECTORY_RINGTONES = "Ringtones";
    public static String DIRECTORY_SCREENSHOTS = "Screenshots";
    public static String DIRECTORY_MUSIC = "Music";*/
    public enum DirectoryType {
        DIRECTORY_ALARMS("Alarms"),
        DIRECTORY_AUDIOBOOKS("Audiobooks"),
        DIRECTORY_DCIM("DCIM"),
        DIRECTORY_DOCUMENTS("Documents"),
        DIRECTORY_DOWNLOADS("Download"),
        DIRECTORY_MOVIES("Movies"),
        DIRECTORY_NOTIFICATIONS("Notifications"),
        DIRECTORY_PICTURES("Pictures"),
        DIRECTORY_PODCASTS("Podcasts"),
        DIRECTORY_RINGTONES("Ringtones"),
        DIRECTORY_SCREENSHOTS("Screenshots"),
        DIRECTORY_MUSIC("Music"),
        NULL("null"),
        NONE("none");
        private String value;

        private DirectoryType(String argValue) {
            value = argValue;
        }

        public String toString() {
            return value;
        }
    }
}
/*
void deleteRecursive(File fileOrDirectory) {
    if (fileOrDirectory.isDirectory())
        for (File child : fileOrDirectory.listFiles())
            deleteRecursive(child);

    fileOrDirectory.delete();
}
*/
/*
String dirName = "TestDirectory";
String fileName = "NewTextFile.txt";
if (ExternalStorage.isExternalStorageAvailable()) {
    String directoryPath = null;
    if (!ExternalStorage.isExternalStorageReadable()) {
        directoryPath = ExternalStorage.getDirPath();
    }
    if (directoryPath != null) {
        if (!ExternalStorage.isDirWriteable(directoryPath)) {
            directoryPath = ExternalStorage.getPublicDirPath(context, dirName);
        }
    }
    String fileData = directoryPath + " hi this is a text file. " + directoryPath;
    TextFile.writeData(fileData, directoryPath, fileName);
    System.out.println("======================> " + TextFile.readData(directoryPath + "/" + fileName));
}
*/
/*
String dirName = "TestDirectory";
String fileName = "test_one.txt";
//String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.DIRECTORY_DOCUMENTS, dirName, true);
String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.NULL, dirName);
String fileData = " hi this is a text file. " + directoryPath;
TextFile.writeData(fileData, directoryPath, fileName);
System.out.println("======================> " + TextFile.readData(directoryPath + "/" + fileName));
ExternalStorage.onDelete(directoryPath);
//ExternalStorage.onDeleteFile(directoryPath, fileName);
ExternalStorage.onForceDelete(directoryPath);
*/
/*
String dirName = "TestDirectory";
String fileName = "test.txt";
//String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.DIRECTORY_DOCUMENTS, dirName, true);
String directoryPath = ExternalStorage.getWriteableDirectory(context, ExternalStorage.DirectoryType.NULL, dirName);
String fileData = " hi this is a text file. " + directoryPath;
TextFile.writeData(fileData, directoryPath, fileName);
System.out.println("======================> " + TextFile.readData(directoryPath + "/" + fileName));
//ExternalStorage.onForceDelete(directoryPath);
*/
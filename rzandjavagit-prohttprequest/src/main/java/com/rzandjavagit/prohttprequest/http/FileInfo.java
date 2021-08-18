package com.rzandjavagit.prohttprequest.http;

public class FileInfo {
    private String key;
    private String fileName;
    private String filePath;

    public FileInfo() {
        //System.out.println("========================|CONSTRUCTOR_FILE_INFO");
    }

    public FileInfo(String argKey, String argFileName, String argFilePath) {
        this.key = argKey;
        this.fileName = argFileName;
        this.filePath = argFilePath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String argKey) {
        this.key = argKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String argFileName) {
        this.fileName = argFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String argFilePath) {
        this.filePath = argFilePath;
    }

    public static FileInfo set(String argKey, String argFileName, String argFilePath) {
        return new FileInfo(argKey, argFileName, argFilePath);
    }
}

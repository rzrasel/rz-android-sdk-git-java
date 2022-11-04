package com.rzandjavagit.prohttprequest.http;

import java.util.ArrayList;
import java.util.List;

public class File {
    private String key;
    private String value;
    private List<FileInfo> params = new ArrayList<FileInfo>();

    public File() {
        //System.out.println("========================|CONSTRUCTOR_FILE");
        params = new ArrayList<FileInfo>();
        params.clear();
    }

    public File setFile(String argKey, String argFileName, String argFilePath) {
        java.io.File file = new java.io.File(argFilePath);
        if (file.exists()) {
            params.add(FileInfo.set(argKey, argFileName, argFilePath));
        }
        return this;
    }

    public File setFile(List<FileInfo> argParams) {
        for (FileInfo item : argParams) {
            String key = item.getKey();
            String fileName = item.getFileName();
            String filePath = item.getFilePath();
            java.io.File file = new java.io.File(filePath);
            if (file.exists()) {
                params.add(FileInfo.set(key, fileName, filePath));
            }
        }
        return this;
    }

    public List<FileInfo> rawFile() {
        return params;
    }
}

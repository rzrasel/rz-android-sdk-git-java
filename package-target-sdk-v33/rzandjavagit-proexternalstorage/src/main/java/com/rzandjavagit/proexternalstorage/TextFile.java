package com.rzandjavagit.proexternalstorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFile {
    //write data to file
    //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void writeData(String argData, String argDirectoryPath, String argFileName) {
        /*String fileName = "test.text";
        argFilePath = fileName;*/
        /*File outputFile = new File(outputDir, tarEntry.getName());
        File outputDirectory = outputFile.getParent();*/
        File outputFile = new File(argDirectoryPath + File.separator + argFileName);
        if (!outputFile.exists()) {
            System.out.println("===================================> File not exists " + outputFile.toString());
            //return;
        }
        System.out.println("===================================> File exists " + outputFile.toString());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            //fileOutputStream.write(argData.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write(argData.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read data from the file
    public static String readData(String argFilePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(argFilePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String temp;
            while ((temp = reader.readLine()) != null) {
                stringBuilder.append(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}

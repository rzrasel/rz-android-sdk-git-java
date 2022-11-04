package com.rzandjavagit.proaudiorecord;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

public class ProMp3AudioRecorder {
    private MediaRecorder recorder = new MediaRecorder();
    private String path;

    public ProMp3AudioRecorder(String argPath) {
        this.path = sanitizePath(argPath);
        recorder = new MediaRecorder();
    }

    private String sanitizePath(String argPath) {
        /*if (!argPath.startsWith("/")) {
            argPath = "/" + argPath;
        }
        if (!path.contains(".")) {
            path += ".3gp";
        }*/
        //return Environment.getExternalStorageDirectory().getAbsolutePath() + path;
        //argPath += ".mp3";
        return argPath;
    }

    public void start() throws IOException {
        String state = android.os.Environment.getExternalStorageState();
        if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
            throw new IOException("SD Card is not mounted.  It is " + state
                    + ".");
        }
        // make sure the directory we plan to store the recording in exists
        File directory = new File(path).getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Path to file could not be created.");
        }

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        recorder.setOutputFile(path);
        recorder.prepare();
        recorder.start();
    }

    public void stop() throws IOException {
        recorder.stop();
        recorder.reset();
        recorder.release();
        recorder = null;
    }

    public void playerCoding(String path) throws IOException {
        MediaPlayer mp = new MediaPlayer();
        mp.setDataSource(path);
        mp.prepare();
        mp.start();
        mp.setVolume(10, 10);
    }
}

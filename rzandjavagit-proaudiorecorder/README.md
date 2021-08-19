# rzandjavagit-proaudiorecorder
Rz Android Java Git SDK

### GIT Command
```code_work
https://github.com/kailash09dabhi/OmRecorder
//
private Recorder recorder;
//
onSetupRecorder();
recorder.startRecording();
//
new CountDownTimer(10000, 1000) {
public void onTick(long millisUntilFinished) {
    //here you can have your logic to set text to edittext
    System.out.println("========================|AUDIO_RECORDED|: " + audioFileFullPath);
}

public void onFinish() {
    System.out.println("========================|AUDIO_RECORDING_DONE|: " + audioFileFullPath);
    try {
        recorder.stopRecording();
        onSetupHTTPRequest(audioFileFullPath);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}.start();
//
private void onSetupRecorder() {
    // Setup recorder
    audioRecDirPath = getAudioBaseDir(null);
    audioRecFileName = getWAVFileName(null) + ".wav";
    audioFileFullPath = audioRecDirPath + "/" + audioRecFileName;
    System.out.println("======================>AUDIO_PATH: " + audioFileFullPath);
    recorder = ProRecorder.wav(
            new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                @Override
                public void onAudioChunkPulled(AudioChunk audioChunk) {
                    animateVoice((float) (audioChunk.maxAmplitude() / 200.0));
                }
            }), file(audioRecDirPath, audioRecFileName));
}
//
private PullableSource mic() {
    return new PullableSource.Default(
            new AudioRecordConfig.Default(
                    MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT,
                    AudioFormat.CHANNEL_IN_MONO, 44100
            )
    );
}

private void animateVoice(final float maxPeak) {
    //recordButton.animate().scaleX(1 + maxPeak).scaleY(1 + maxPeak).setDuration(10).start();
}

@NonNull
private File file(String dirPath, String argFileName) {
    return new File(dirPath, argFileName);
}

private String getAudioBaseDir(String argDirName) {
    if (argDirName == null) {
        argDirName = getAppName(context) + "/audio-recorded";
    } else {
        argDirName = getAppName(context);
    }
    return ProExternalStorage.getBaseDir(context, argDirName, true);
}

private String getWAVFileName(String argFileName) {
    if (argFileName == null) {
        argFileName = "audio-recorded-voice";
    }
    return argFileName;
}
```
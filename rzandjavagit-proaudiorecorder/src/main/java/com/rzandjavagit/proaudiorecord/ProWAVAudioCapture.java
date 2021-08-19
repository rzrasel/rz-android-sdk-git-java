package com.sm.banglaalphabet.proaudiorecorder;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class ProWAVAudioCapture {
    private static final String TAG = ProWAVAudioCapture.class.getName();

    public static final String CODEC_WAV = "audio/wav";
    public static final int CODEC_WAV_SAMPLE_RATE = 0;

    public static final String CODEC_OPUS = "audio/opus";
    public static final int CODEC_OPUS_SAMPLE_RATE = 48000;

    private String username;
    private String password;
    private String token;
    private boolean learningOptOut;
    private String voice;
    private String content;
    private String codec;
    private int sampleRate;
    private String server;
    private AudioTrack audioTrack;
    private int bufferSize;

    public ProWAVAudioCapture() {
        this.codec = CODEC_WAV;
        // By default, the sample rate would be detected by the SDK if the value is set to zero
        // However, the metadata is not reliable, need to decode at the maximum sample rate
        this.sampleRate = 48000;
        initPlayer();
    }

    private void initPlayer() {
        //stopTtsPlayer();
        // IMPORTANT: minimum required buffer size for the successful creation of an AudioTrack instance in streaming mode.
        bufferSize = AudioTrack.getMinBufferSize(sampleRate,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT);

        synchronized (this) {
            audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufferSize,
                    AudioTrack.MODE_STREAM);
            if (audioTrack != null)
                audioTrack.play();
        }
    }
}
//https://github.com/learntodroid/AudioRecorder
//https://github.com/watson-developer-cloud/speech-android-sdk
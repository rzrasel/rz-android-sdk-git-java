package com.rzandroidgit.appusagesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.rzandjavagit.proadmob.ProAdMobManager;
import com.rzandjavagit.proadmob.ProConfigData;

public class ActivityProAdMob extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private ProConfigData proConfigData;
    private ProAdMobManager proAdMobManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_ad_mob);
        activity = this;
        context = this;
        proConfigData = new ProConfigData(
                140,
                70,
                22,
                12,
                4.0,
                2.0,
                2.6,
                2.2,
                true,
                false
        );
        proAdMobManager = new ProAdMobManager.Builder()
                .setEventListener(new SetAdEventListener())
                .setConfigData(proConfigData)
                .setIsDebug(proConfigData.isDebug)
                .build(activity, context);
    }

    private static class SetAdEventListener implements ProAdMobManager.OnAdEventListener {
        @Override
        public void onAdLoaded() {
        }

        @Override
        public void onAdFailedToLoad(String adError) {
        }

        @Override
        public void onAdShowedFullScreenContent() {
        }

        @Override
        public void onAdDismissedFullScreenContent() {
        }

        @Override
        public void onAdFailedToShowFullScreenContent(String adError) {
        }
    }
}
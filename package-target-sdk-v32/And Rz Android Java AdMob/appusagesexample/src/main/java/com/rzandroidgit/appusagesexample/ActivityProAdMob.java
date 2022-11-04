package com.rzandroidgit.appusagesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*import com.rzandjavagit.proadmob.ProAdMobManager;
import com.rzandjavagit.proadmob.ProConfigData;*/

public class ActivityProAdMob extends AppCompatActivity {
    private Activity activity;
    private Context context;
    private AppApplication appApplication;
    /*private ProConfigData proConfigData;
    private ProAdMobManager proAdMobManager;*/
    private Button sysBtnProAdMob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_ad_mob);
        activity = this;
        context = this;
        appApplication = (AppApplication) getApplicationContext();
        appApplication.setActivity(activity)
                .setContext(context)
                .runAdMob();
        //
        sysBtnProAdMob = (Button) findViewById(R.id.sysBtnProAdMob);
        //
        /*proConfigData = new ProConfigData(
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
        );*/
        /*proAdMobManager = new ProAdMobManager.Builder()
                .setEventListener(new SetAdEventListener())
                .setConfigData(proConfigData)
                .setIsDebug(proConfigData.isDebug)
                .build(activity, context);*/
        //
        //proAdMobManager.onDebugPrint();
        //proAdMobManager.onClearPreferences();
        sysBtnProAdMob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*proAdMobManager.onButtonClick();
                onLoadAd();*/
                appApplication.setEventClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*proAdMobManager.onResume();
        onLoadAd();*/
        appApplication.setResumed();
    }

    /*private void onLoadAd() {
        if (proAdMobManager.canShowAdView(false)) {
            System.out.println("DEBUG_LOG_PRINT: you can show");
            String admobAdUnitId = context.getResources().getString(R.string.admob_inters_ad_unit_id);
            proAdMobManager.onLoadAd(admobAdUnitId);
        } else {
            System.out.println("DEBUG_LOG_PRINT: you can not show");
        }
        proAdMobManager.onDebugPrint();
    }*/

    /*private static class SetAdEventListener implements ProAdMobManager.OnAdEventListener {
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
    }*/

    private void showToast(String argMessage) {
        Toast.makeText(context, argMessage, Toast.LENGTH_LONG).show();
    }
}
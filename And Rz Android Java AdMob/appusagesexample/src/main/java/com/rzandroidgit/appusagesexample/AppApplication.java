package com.rzandroidgit.appusagesexample;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.rzandjavagit.proadmob.ProAdMobManager;
import com.rzandjavagit.proadmob.ProConfigData;

public class AppApplication extends Application {
    private Activity activity;
    private Context context;
    private static AppApplication singleton;
    private ProConfigData proConfigData;
    private ProAdMobManager proAdMobManager;
    private boolean isShowAd = true;

    public AppApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public AppApplication setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public AppApplication setContext(Context context) {
        this.context = context;
        return this;
    }

    public void runAdMob() {
        new OnSetupInitialization()
                .onSetupProConfigData()
                .onSetupProAdMobManager();
    }

    public void setEventClicked() {
        setEventClicked(true);
    }

    public void setEventClicked(boolean isShowAd) {
        this.isShowAd = isShowAd;
        proAdMobManager.onButtonClick();
        onLoadAd();
    }

    public void setResumed() {
        setResumed(true);
    }

    public void setResumed(boolean isShowAd) {
        this.isShowAd = isShowAd;
        proAdMobManager.onResume();
        onLoadAd();
    }

    private void onLoadAd() {
        if (proAdMobManager.canShowAdView(false)) {
            System.out.println("DEBUG_LOG_PRINT: you can show");
            if (isShowAd) {
                System.out.println("DEBUG_LOG_PRINT: you can show but isShowAd is false");
                String admobAdUnitId = context.getResources().getString(R.string.admob_inters_ad_unit_id);
                proAdMobManager.onLoadAd(admobAdUnitId);
            }
        } else {
            System.out.println("DEBUG_LOG_PRINT: you can not show");
        }
        proAdMobManager.onDebugPrint();
    }

    private class OnSetupInitialization {
        private OnSetupInitialization onSetupProConfigData() {
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
            return this;
        }

        private OnSetupInitialization onSetupProAdMobManager() {
            proAdMobManager = new ProAdMobManager.Builder()
                    .setEventListener(new SetAdEventListener())
                    .setConfigData(proConfigData)
                    .setIsDebug(proConfigData.isDebug)
                    .build(activity, context);
            return this;
        }
    }

    private class SetAdEventListener implements ProAdMobManager.OnAdEventListener {
        @Override
        public void onAdLoaded() {
            proAdMobManager.showAd();
            System.out.println("DEBUG_LOG_PRINT_ADMOB: onAdLoaded()");
        }

        @Override
        public void onAdFailedToLoad(String adError) {
            System.out.println("DEBUG_LOG_PRINT_ADMOB: onAdFailedToLoad(String adError) " + adError);
        }

        @Override
        public void onAdShowedFullScreenContent() {
            System.out.println("DEBUG_LOG_PRINT_ADMOB: onAdShowedFullScreenContent()");
        }

        @Override
        public void onAdDismissedFullScreenContent() {
            System.out.println("DEBUG_LOG_PRINT_ADMOB: onAdDismissedFullScreenContent()");
        }

        @Override
        public void onAdFailedToShowFullScreenContent(String adError) {
            System.out.println("DEBUG_LOG_PRINT_ADMOB: onAdFailedToShowFullScreenContent(String adError) " + adError);
        }
    }
}

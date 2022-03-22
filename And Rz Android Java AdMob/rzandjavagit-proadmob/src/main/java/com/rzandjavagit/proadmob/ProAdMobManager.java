package com.rzandjavagit.proadmob;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.ads.AdRequest;

public class ProAdMobManager {
    private Activity activity;
    private Context context;
    private ProConfigData proConfigData;
    private OnAdEventListener adEventListener;
    private ProPreferences proPreferences;
    private ProAdMobHelper proAdMobHelper;
    private ProFirebaseLogEvent proFirebaseLogEvent;
    private ProPrefAdMobDataManager proPrefAdMobDataManager;
    private final int timeSecondMax = 70;
    private final int timeSecondMin = 30;
    private final double timeOverFactorMax = 3.3;
    private final double timeOverFactorMin = 2.0;
    private final int eventMax = 22;
    private final int eventMin = 12;
    private boolean isDebug;

    public ProAdMobManager(Builder builder) {
        this.activity = builder.activity;
        this.context = builder.context;
        this.proConfigData = builder.proConfigData;
        this.adEventListener = builder.eventListener;
        this.isDebug = builder.isDebug;
        //
        new OnSetupInitialization()
                .onSetupPrefAdMobDataManager()
                .onSetupAdMobHelper()
                .onSetupFirebaseLogEvent();
    }

    public boolean canShowAdView(boolean isForced) {
        ProPrefAdMobDataManager.AdViewDataManager adViewDataManager = proPrefAdMobDataManager.new AdViewDataManager();
        return adViewDataManager.canShowAdView(isForced);
    }

    public void onButtonClick() {
        ProPrefAdMobDataManager.AdViewDataManager adViewDataManager = proPrefAdMobDataManager.new AdViewDataManager();
        adViewDataManager.onButtonClick();
    }

    public void onResume() {
        ProPrefAdMobDataManager.AdViewDataManager adViewDataManager = proPrefAdMobDataManager.new AdViewDataManager();
        adViewDataManager.onResume();
    }

    public void onResetPreference() {
        proPrefAdMobDataManager.onResetPreference();
    }

    public void onClearPreferences() {
        proPrefAdMobDataManager.onClearPreferences();
    }

    public void onDebugPrint() {
        //proPreferences.debugPrint()
        proPrefAdMobDataManager.onLogPreferences();
    }

    public AdRequest getAdRequest() {
        return proAdMobHelper.getAdRequest();
    }

    public void onLoadAd(String admobAdUnitId) {
        if (isDebug) {
            return;
        }
        proAdMobHelper.onLoadAd(admobAdUnitId);
        ProFirebaseLogEvent.LogEvent logEvent = ProFirebaseLogEvent.LogEvent.ADMOB_REQUEST_FOR_LOAD;
        String logEventMessage = "admob-state: Request for admob";
        proFirebaseLogEvent.onLogEvent(logEvent, logEventMessage);
    }

    public void onPrepareAd(AdRequest adRequest, String admobAdUnitId) {
        if (isDebug) {
            return;
        }
        proAdMobHelper.onPrepareAd(adRequest, admobAdUnitId);
        ProFirebaseLogEvent.LogEvent logEvent = ProFirebaseLogEvent.LogEvent.ADMOB_REQUEST_FOR_LOAD;
        String logEventMessage = "admob-state: Request for admob";
        proFirebaseLogEvent.onLogEvent(logEvent, logEventMessage);
    }

    public void showAd() {
        proAdMobHelper.show();
        //onProPrefInitialize(true)
        //onSavePreference()
        proPrefAdMobDataManager.onRestartPreference();
    }

    public static class Builder {
        private Activity activity;
        private Context context;
        private ProConfigData proConfigData;
        private OnAdEventListener eventListener;
        private boolean isDebug = false;

        public Builder setEventListener(OnAdEventListener adEventListener) {
            this.eventListener = adEventListener;
            return this;
        }

        public Builder setConfigData(ProConfigData proConfigData) {
            this.proConfigData = proConfigData;
            return this;
        }

        public Builder setIsDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public ProAdMobManager build(Activity activity, Context context) {
            this.activity = activity;
            this.context = context;
            return new ProAdMobManager(this);
        }
    }

    private class OnSetupInitialization {
        private OnSetupInitialization onSetupPrefAdMobDataManager() {
            if (proConfigData == null) {
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
                        true,
                        false
                );
            }
            proPrefAdMobDataManager = new ProPrefAdMobDataManager.Builder()
                    .build(activity, context, proConfigData);
            return this;
        }

        private OnSetupInitialization onSetupAdMobHelper() {
            proAdMobHelper = new ProAdMobHelper(activity, context)
                    .setEventListener(new SetAdEventListener())
                    .setIsDebug(isDebug);
            return this;
        }

        private OnSetupInitialization onSetupFirebaseLogEvent() {
            proFirebaseLogEvent = new ProFirebaseLogEvent.Builder()
                    .isLogEvent(proConfigData.isFirebaseLogEvent)
                    .build(activity, context);
            return this;
        }
    }

    private class SetAdEventListener implements ProAdMobHelper.OnAdEventListener {
        public void onAdLoaded() {
            adEventListener.onAdLoaded();
            ProFirebaseLogEvent.LogEvent logEvent = ProFirebaseLogEvent.LogEvent.ADMOB_SUCCESS_TO_LOAD;
            String logEventMessage = "admob-state: onAdLoaded()";
            proFirebaseLogEvent.onLogEvent(logEvent, logEventMessage);
        }

        public void onAdFailedToLoad(String adError) {
            adEventListener.onAdFailedToLoad(adError);
            ProFirebaseLogEvent.LogEvent logEvent = ProFirebaseLogEvent.LogEvent.ADMOB_FAILED_TO_LOAD;
            String logEventMessage = "admob-state: onAdFailedToLoad(String adError) " + adError;
            proFirebaseLogEvent.onLogEvent(logEvent, logEventMessage);
        }

        public void onAdShowedFullScreenContent() {
            adEventListener.onAdShowedFullScreenContent();
        }

        public void onAdDismissedFullScreenContent() {
            adEventListener.onAdDismissedFullScreenContent();
        }

        public void onAdFailedToShowFullScreenContent(String adError) {
            adEventListener.onAdFailedToShowFullScreenContent(adError);
        }
    }

    public interface OnAdEventListener {
        void onAdLoaded();

        void onAdFailedToLoad(String adError);

        void onAdShowedFullScreenContent();

        void onAdDismissedFullScreenContent();

        void onAdFailedToShowFullScreenContent(String adError);
    }
}

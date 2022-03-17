package com.rzandjavagit.proadmob;

import android.app.Activity;
import android.content.Context;

public class ProAdMobManager {
    private Activity activity;
    private Context context;
    private ProConfigData proConfigData;
    private OnAdEventListener adEventListener;
    private ProPreferences proPreferences;
    private ProAdMobHelper proAdMobHelper;
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
        new OnSetupInitialization().onSetupPrefAdMobDataManager();
        //
        proAdMobHelper = new ProAdMobHelper(activity, context)
                .setEventListener(new SetAdEventListener())
                .setIsDebug(isDebug);
    }

    private class OnSetupInitialization {
        OnSetupInitialization onSetupPrefAdMobDataManager() {
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
                        false
                );
            }
            proPrefAdMobDataManager = new ProPrefAdMobDataManager.Builder()
                    .build(activity, context, proConfigData);
            return this;
        }
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

    class SetAdEventListener implements ProAdMobHelper.OnAdEventListener {
        public void onAdLoaded() {
            adEventListener.onAdLoaded();
        }

        public void onAdFailedToLoad(String adError) {
            adEventListener.onAdFailedToLoad(adError);
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

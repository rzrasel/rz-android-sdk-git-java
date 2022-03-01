package com.rzandjavagit.proadmob;

import android.app.Activity;
import android.content.Context;

public class ProAdMobManager {
    private Activity activity;
    private Context context;
    private OnAdEventListener adEventListener;
    private ProPreferences proPreferences;
    private ProAdMobHelper proAdMobHelper;
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
        this.isDebug = builder.isDebug;
        adEventListener = builder.eventListener;
        proAdMobHelper = new ProAdMobHelper(activity, context)
                .setEventListener(new SetAdEventListener())
                .setIsDebug(isDebug);
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

    class Builder {
        private Activity activity;
        private Context context;
        private OnAdEventListener eventListener;
        private boolean isDebug = false;

        public Builder setEventListener(OnAdEventListener adEventListener) {
            this.eventListener = adEventListener;
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

    public interface OnAdEventListener {
        void onAdLoaded();

        void onAdFailedToLoad(String adError);

        void onAdShowedFullScreenContent();

        void onAdDismissedFullScreenContent();

        void onAdFailedToShowFullScreenContent(String adError);
    }
}

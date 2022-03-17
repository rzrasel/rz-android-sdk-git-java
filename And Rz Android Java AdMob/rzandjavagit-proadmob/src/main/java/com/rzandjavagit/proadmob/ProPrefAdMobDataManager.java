package com.rzandjavagit.proadmob;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;

class ProPrefAdMobDataManager {
    private Activity activity;
    private Context context;
    private Gson gson = new Gson();
    private ProPrefAdMobData proPrefAdMobData;
    private ProConfigData proConfigData;
    private ProPreferences proPreferences;

    ProPrefAdMobDataManager(Builder builder) {
        this.activity = builder.activity;
        this.context = builder.context;
        this.proConfigData = builder.proConfigData;
    }

    private void onSetupProPreferences() {
        proPreferences = new ProPreferences.Builder()
                .withContext(context)
                .withPrefsName(context.getPackageName())
                .withMode(ProPreferences.Mode.PRIVATE)
                .withDefaultPrefs(false)
                .build();
    }

    public ProPrefAdMobData onSetupPrefData() {
        boolean isInitialized = true;
        long lastTimeMillis = System.currentTimeMillis();
        long lastTimeSeconds = lastTimeMillis / 1000;
        //
        int nextRandSeconds = AdMobUtils.getRandomId(proConfigData.minTimeInSecond, proConfigData.maxTimeInSecond);
        int totalEventForNext = AdMobUtils.getRandomId(proConfigData.minEvent, proConfigData.maxEvent);
        //
        long nextTimeSeconds = lastTimeSeconds + nextRandSeconds;
        long nextTimeMillis = nextTimeSeconds * 1000;
        long nextRemainTimeSeconds = nextTimeSeconds - lastTimeSeconds;
        int totalButtonClickEvent = 0;
        int totalViewResumeEvent = 0;
        int totalEventCounter = 0;
        //
        float randTimeFactorOffset = AdMobUtils.getDecimalFormat(AdMobUtils.getRandomFloat(proConfigData.minTimeOffset, proConfigData.maxTimeOffset));
        int totalTimeFactorOffset = (int) AdMobUtils.getDecimalFormat(nextRandSeconds * randTimeFactorOffset);
        long totalTimeFactorSeconds = lastTimeSeconds + totalTimeFactorOffset;
        float randEventOffset = AdMobUtils.getDecimalFormat(AdMobUtils.getRandomFloat(proConfigData.minEventOffset, proConfigData.maxEventOffset));
        int totalEventOffset = (int) AdMobUtils.getDecimalFormat((totalEventForNext / randEventOffset) * randEventOffset);
        boolean isRandomizeAdId = proConfigData.isRandomizeAdId;
        //
        return new ProPrefAdMobData(
                isInitialized,
                lastTimeMillis,
                lastTimeSeconds,
                nextRandSeconds,
                nextTimeMillis,
                nextTimeSeconds,
                nextRemainTimeSeconds,
                totalEventForNext,
                totalButtonClickEvent,
                totalViewResumeEvent,
                totalEventCounter,
                randTimeFactorOffset,
                totalTimeFactorOffset,
                totalTimeFactorSeconds,
                randEventOffset,
                totalEventOffset,
                isRandomizeAdId
        );
    }

    public String getJson(ProPrefAdMobData proPrefAdMobData) {
        return gson.toJson(proPrefAdMobData);
    }

    public String getJson(String json) {
        return getJson(fromJson(json));
    }

    public ProPrefAdMobData fromJson(ProPrefAdMobData proPrefAdMobData) {
        return fromJson(getJson(proPrefAdMobData));
    }

    public ProPrefAdMobData fromJson(String json) {
        return gson.fromJson(json, ProPrefAdMobData.class);
    }

    public String getString(ProPrefAdMobData proPrefAdMobData) {
        return getJson(proPrefAdMobData);
    }

    public long getCurrentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static class Builder {
        private Activity activity;
        private Context context;
        private ProConfigData proConfigData;

        public ProPrefAdMobDataManager build(Activity activity, Context context, ProConfigData proConfigData) {
            this.activity = activity;
            this.context = context;
            this.proConfigData = proConfigData;
            return new ProPrefAdMobDataManager(this);
        }
    }

    public void onLogPrint() {
        onLogPrint(proPrefAdMobData);
    }

    public void onLogPrint(ProPrefAdMobData proPrefAdMobData) {
        System.out.println("DEBUG_LOG_PRINT_ADMOB: ProPrefAdMobDataManager->onLogPrint(proPrefAdMobData: ProPrefAdMobData)");
        System.out.println("DEBUG_LOG_PRINT_ADMOB: ProPrefAdMobData -> " + getString(proPrefAdMobData));
    }

    private String getString(ProConfigData proConfigData) {
        return gson.toJson(proConfigData);
    }

    public void onConfigLogPrint() {
        onLogPrint(proConfigData);
    }

    public void onLogPrint(ProConfigData proConfigData) {
        System.out.println("DEBUG_LOG_PRINT_ADMOB: ProPrefAdMobDataManager->onLogPrint(proConfigData: ProConfigData)");
        System.out.println("DEBUG_LOG_PRINT_ADMOB: ProConfigData -> " + getString(proConfigData));
    }

    public void onLogPreferences() {
        System.out.println("DEBUG_LOG_PRINT_ADMOB: ProPrefAdMobDataManager->onLogPreferences()");
        System.out.println("DEBUG_LOG_PRINT_ADMOB: proPreferences ->");
        proPreferences.debugPrint();
    }

    public enum PrefKey {
        ADMOB_JSON_MODEL_CLASS_DATA("admob_json_model_class_data"),
        NONE("none");
        public String label;

        PrefKey(String label) {
            this.label = label;
        }

        public static PrefKey getItem(String label) {
            for (PrefKey item : values()) {
                if (item.label.equals(label)) {
                    return item;
                }
            }
            return null;
        }
    }
}
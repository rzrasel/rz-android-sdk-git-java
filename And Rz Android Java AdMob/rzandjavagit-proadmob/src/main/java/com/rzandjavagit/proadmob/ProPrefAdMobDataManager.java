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
        onSetupProPreferences();
        //
        String jsonString =
                proPreferences.getString(PrefKey.ADMOB_JSON_MODEL_CLASS_DATA.label, null);
        if (jsonString == null) {
            proPrefAdMobData = onSetupPrefData();
            onSavePreference();
        } else {
            proPrefAdMobData = fromJson(jsonString);
        }
        //
        //onLogPrint(proPrefAdMobData);
        //proPreferences.clear();
        //onProPrefInitialize(false);
        //proPreferences.debugPrint();
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
        double randTimeFactorOffset = AdMobUtils.getDecimalFormat(AdMobUtils.getRandomDouble(proConfigData.minTimeOffset, proConfigData.maxTimeOffset));
        int totalTimeFactorOffset = (int) AdMobUtils.getDecimalFormat(nextRandSeconds * randTimeFactorOffset);
        long totalTimeFactorSeconds = lastTimeSeconds + totalTimeFactorOffset;
        double randEventOffset = AdMobUtils.getDecimalFormat(AdMobUtils.getRandomDouble(proConfigData.minEventOffset, proConfigData.maxEventOffset));
        int totalEventOffset = (int) AdMobUtils.getDecimalFormat(totalEventForNext / (int) Math.ceil(randEventOffset));
        //int totalEventOffset = (int) AdMobUtils.getDecimalFormat(totalEventForNext / randEventOffset);
        totalEventOffset = totalEventOffset * (int) Math.floor(randEventOffset);
        String showsFromCondition = "none initialization";
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
                showsFromCondition,
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

    public class AdViewDataManager {
        private long nextTimeDiffInMillis(ProPrefAdMobData proPrefAdMobData) {
            return proPrefAdMobData.nextTimeMillis - System.currentTimeMillis();
        }

        private long nextTimeDiffInSeconds(ProPrefAdMobData proPrefAdMobData) {
            return proPrefAdMobData.nextTimeSeconds - getCurrentSeconds();
        }

        private boolean canShowByForced(ProPrefAdMobData proPrefAdMobData) {
            int eventRemain = proPrefAdMobData.totalEventForNextViewing - proPrefAdMobData.totalEventCount;
            if (nextTimeDiffInSeconds(proPrefAdMobData) < 0 || eventRemain < 0) {
                return true;
            }
            return false;
        }


        private boolean canPassByRegular(ProPrefAdMobData proPrefAdMobData) {
            int eventRemain = proPrefAdMobData.totalEventForNextViewing - proPrefAdMobData.totalEventCount;
            if (nextTimeDiffInSeconds(proPrefAdMobData) < 0 && eventRemain < 0) {
                return true;
            }
            return false;
        }

        private boolean isMaxTimeOver(ProPrefAdMobData proPrefAdMobData) {
            long totalTimeFactor = proPrefAdMobData.totalTimeFactorSeconds - getCurrentSeconds();
            int totalEventFactor = proPrefAdMobData.totalEventOffset - proPrefAdMobData.totalEventCount;
            if (totalTimeFactor < 0 && totalEventFactor < 0) {
                return true;
            }
            return false;
        }

        public boolean canShowAdView(boolean isForced) {
            //var retVal = false
            //onSavePreference();
            //onLogPrint(proPrefAdMobData)
            if (canShowByForced(proPrefAdMobData) && isForced) {
                proPrefAdMobData.showsFromCondition = "if (canShowByForced(proPrefAdMobData) && isForced)";
                onSavePreference();
                return true;
            }
            if (canPassByRegular(proPrefAdMobData)) {
                proPrefAdMobData.showsFromCondition = "if (canPassByRegular(proPrefAdMobData))";
                onSavePreference();
                return true;
            }
            if (isMaxTimeOver(proPrefAdMobData)) {
                proPrefAdMobData.showsFromCondition = "if (isMaxTimeOver(proPrefAdMobData))";
                onSavePreference();
                return true;
            }
            proPrefAdMobData.showsFromCondition = "condition not matched, can not show";
            onSavePreference();
            return false;
        }

        public void onButtonClick() {
            proPrefAdMobData.totalButtonClickEvent = proPrefAdMobData.totalButtonClickEvent + 1;
            onEventArise();
        }

        public void onResume() {
            proPrefAdMobData.totalViewResumeEvent = proPrefAdMobData.totalViewResumeEvent + 1;
            onEventArise();
        }

        private void onEventArise() {
            proPrefAdMobData.totalEventCount = proPrefAdMobData.totalEventCount + 1;
            onSavePreference();
        }
    }

    public void onRestartPreference() {
        proPrefAdMobData = onSetupPrefData();
        onSavePreference();
    }

    private void setRemainTimeSeconds() {
        proPrefAdMobData.nextRemainTimeSeconds = proPrefAdMobData.nextTimeSeconds - getCurrentSeconds();
    }

    private void onSavePreference() {
        setRemainTimeSeconds();
        String jsonString = getJson(proPrefAdMobData);
        proPreferences.putString(PrefKey.ADMOB_JSON_MODEL_CLASS_DATA.label, jsonString);
    }

    public void onClearPreferences() {
        proPreferences.clear();
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
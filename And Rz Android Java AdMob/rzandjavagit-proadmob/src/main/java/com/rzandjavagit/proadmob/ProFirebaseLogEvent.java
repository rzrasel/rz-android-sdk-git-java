package com.rzandjavagit.proadmob;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProFirebaseLogEvent {
    private Activity activity;
    private Context context;
    private FirebaseAnalytics firebaseAnalytics;
    private Bundle analyticsBundle;
    private boolean isLogEvent;

    public ProFirebaseLogEvent(Builder builder) {
        this.activity = builder.activity;
        this.context = builder.context;
        this.isLogEvent = builder.isLogEvent;
    }

    public void onLogEvent(LogEvent logEvent, String logMessage) {
        if (!isLogEvent) {
            return;
        }
        String strDate = logEvent.label + getDateForm("yyyy_MM_dd");
        onLogEventRun(strDate, logMessage);
        strDate = logEvent.label + getDateForm("yyyy_MM");
        onLogEventRun(strDate, logMessage);
    }

    public static class Builder {
        private Activity activity;
        private Context context;
        private boolean isLogEvent;

        public Builder isLogEvent(boolean isLogEvent) {
            this.isLogEvent = isLogEvent;
            return this;
        }

        public ProFirebaseLogEvent build(Activity activity, Context context) {
            this.activity = activity;
            this.context = context;
            return new ProFirebaseLogEvent(this);
        }
    }

    private void onLogEventRun(String logEventFormattedDate, String logMessage) {
        if (!isLogEvent) {
            return;
        }
        analyticsBundle = new Bundle();
        analyticsBundle.putString(logEventFormattedDate, logMessage);
        firebaseAnalytics.logEvent(logEventFormattedDate, analyticsBundle);
    }

    private String getDateForm() {
        return getDateForm("yyyy_MM_dd");
    }

    private String getDateForm(String dateFormat) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        String strDate = simpleDateFormat.format(date);
        return simpleDateFormat.format(date);
    }

    public enum LogEvent {
        ADMOB_REQUEST_FOR_LOAD("admob_request_"),
        ADMOB_REQUEST_DEBUG_MODE("admob_request_debug_"),
        ADMOB_SUCCESS_TO_LOAD("admob_success_"),
        ADMOB_FAILED_TO_LOAD("admob_failed_"),
        NONE("none");
        public String label;

        LogEvent(String label) {
            this.label = label;
        }

        public static LogEvent getItem(String label) {
            for (LogEvent item : values()) {
                if (item.label.equals(label)) {
                    return item;
                }
            }
            return null;
        }
        /*ON_AD_REQUEST_FOR_LOAD("admob_on_ad_request_for_load"),
        ON_AD_LOADED("admob_on_ad_success_to_load"),
        ON_AD_FAILED_TO_LOAD("admob_on_ad_failed_to_load"),*/
    }
}

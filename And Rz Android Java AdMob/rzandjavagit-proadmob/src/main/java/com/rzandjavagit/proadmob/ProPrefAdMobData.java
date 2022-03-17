package com.rzandjavagit.proadmob;

import com.google.gson.annotations.SerializedName;

class ProPrefAdMobData {
    @SerializedName("admob_data_is_initialized")
    public boolean isInitialized;
    @SerializedName("admob_last_showing_time_millis")
    public long lastTimeMillis;
    @SerializedName("admob_last_showing_time_seconds")
    public long lastTimeSeconds;
    @SerializedName("admob_next_showing_rand_seconds")
    public int nextRandSeconds;
    @SerializedName("admob_next_viewing_time_millis")
    public long nextTimeMillis;
    @SerializedName("admob_next_viewing_time_seconds")
    public long nextTimeSeconds;
    @SerializedName("admob_next_view_remain_time_seconds")
    public long nextRemainTimeSeconds;
    @SerializedName("admob_total_event_for_next_viewing")
    public int totalEventForNextViewing;
    @SerializedName("admob_total_button_click_event")
    public int totalButtonClickEvent;
    @SerializedName("admob_total_view_resume_event")
    public int totalViewResumeEvent;
    @SerializedName("admob_total_event_counter")
    public int totalEventCount;
    @SerializedName("admob_rand_time_factor_offset")
    public double randTimeFactorOffset;
    @SerializedName("admob_total_time_factor_offset")
    public int totalTimeFactorOffset;
    @SerializedName("admob_total_time_factor_seconds")
    public long totalTimeFactorSeconds;
    @SerializedName("admob_rand_event_offset")
    public double randEventOffset;
    @SerializedName("admob_total_event_offset")
    public int totalEventOffset;
    @SerializedName("admob_is_randomize_ad_id")
    public boolean isRandomizeAdId;

    ProPrefAdMobData(
            boolean isInitialized,
            long lastTimeMillis,
            long lastTimeSeconds,
            int nextRandSeconds,
            long nextTimeMillis,
            long nextTimeSeconds,
            long nextRemainTimeSeconds,
            int totalEventForNextViewing,
            int totalButtonClickEvent,
            int totalViewResumeEvent,
            int totalEventCount,
            double randTimeFactorOffset,
            int totalTimeFactorOffset,
            long totalTimeFactorSeconds,
            double randEventOffset,
            int totalEventOffset,
            boolean isRandomizeAdId
    ) {
        this.isInitialized = isInitialized;
        this.lastTimeMillis = lastTimeMillis;
        this.lastTimeSeconds = lastTimeSeconds;
        this.nextRandSeconds = nextRandSeconds;
        this.nextTimeMillis = nextTimeMillis;
        this.nextTimeSeconds = nextTimeSeconds;
        this.nextRemainTimeSeconds = nextRemainTimeSeconds;
        this.totalEventForNextViewing = totalEventForNextViewing;
        this.totalButtonClickEvent = totalButtonClickEvent;
        this.totalViewResumeEvent = totalViewResumeEvent;
        this.totalEventCount = totalEventCount;
        this.randTimeFactorOffset = randTimeFactorOffset;
        this.totalTimeFactorOffset = totalTimeFactorOffset;
        this.totalTimeFactorSeconds = totalTimeFactorSeconds;
        this.randEventOffset = randEventOffset;
        this.totalEventOffset = totalEventOffset;
        this.isRandomizeAdId = isRandomizeAdId;
    }
}
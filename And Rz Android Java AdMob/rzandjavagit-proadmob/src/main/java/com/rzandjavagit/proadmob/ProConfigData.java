package com.rzandjavagit.proadmob;

import com.google.gson.annotations.SerializedName;

public class ProConfigData {
    @SerializedName("max_time_in_second_for_next_ad")
    public int maxTimeInSecond;
    @SerializedName("min_time_in_second_for_next_ad")
    public int minTimeInSecond;
    @SerializedName("max_ui_event_for_next_ad")
    public int maxEvent;
    @SerializedName("min_ui_event_for_next_ad")
    public int minEvent;
    @SerializedName("max_time_offset_for_next_ad")
    public double maxTimeOffset;
    @SerializedName("min_time_offset_for_next_ad")
    public double minTimeOffset;
    @SerializedName("max_ui_event_offset_for_next_ad")
    public double maxEventOffset;
    @SerializedName("min_ui_event_offset_for_next_ad")
    public double minEventOffset;
    @SerializedName("is_randomize_ad_id")
    public boolean isRandomizeAdId;
    @SerializedName("is_firebase_log_event")
    public boolean isFirebaseLogEvent;
    @SerializedName("is_debug_mode")
    public boolean isDebug;

    public ProConfigData(
            int maxTimeInSecond,
            int minTimeInSecond,
            int maxEvent,
            int minEvent,
            double maxTimeOffset,
            double minTimeOffset,
            double maxEventOffset,
            double minEventOffset,
            boolean isRandomizeAdId,
            boolean isFirebaseLogEvent,
            boolean isDebug
    ) {
        this.maxTimeInSecond = maxTimeInSecond;
        this.minTimeInSecond = minTimeInSecond;
        this.maxEvent = maxEvent;
        this.minEvent = minEvent;
        this.maxTimeOffset = maxTimeOffset;
        this.minTimeOffset = minTimeOffset;
        this.maxEventOffset = maxEventOffset;
        this.minEventOffset = minEventOffset;
        this.isRandomizeAdId = isRandomizeAdId;
        this.isFirebaseLogEvent = isFirebaseLogEvent;
        this.isDebug = isDebug;
    }
}
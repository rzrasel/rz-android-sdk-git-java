package com.rzandjavagit.prospotlightview.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created 2017-10-13
 */
public class ProPreferencesManager {

    private static final String PREFERENCES_NAME = "pro_spotlight_view_preferences";

    private SharedPreferences sharedPreferences;

    public ProPreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean isDisplayed(String id) {
        return sharedPreferences.getBoolean(id, false);
    }

    public void setDisplayed(String id) {
        sharedPreferences.edit().putBoolean(id, true).apply();
    }

    public void reset(String id) {
        sharedPreferences.edit().remove(id).apply();
    }

    public void resetAll() {
        sharedPreferences.edit().clear().apply();
    }
}

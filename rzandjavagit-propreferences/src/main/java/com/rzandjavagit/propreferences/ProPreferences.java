package com.rzandjavagit.propreferences;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import java.util.LinkedHashSet;
import java.util.Set;

public class ProPreferences {
    private Context context;
    private static SharedPreferences sharedPrefs;
    private String prefsKey;
    private int prefsMode;
    private static final String DEFAULT_SUFFIX = "_preferences";
    private static final String LENGTH = "#LENGTH";

    private ProPreferences(Builder argBuilder) {
        context = argBuilder.context;
        prefsKey = argBuilder.prefsKey;
        prefsMode = argBuilder.prefsMode;
        initPrefs(context, prefsKey, prefsMode);
    }

    private static void initPrefs(Context argContext, String argPrefsName, int argPrefsMode) {
        sharedPrefs = argContext.getSharedPreferences(argPrefsName, argPrefsMode);
    }

    public static SharedPreferences getPreferences() {
        if (sharedPrefs != null) {
            return sharedPrefs;
        }
        throw new RuntimeException(
                ProPreferences.class.getSimpleName()
                        + " class not correctly instantiated. Please call Builder.setContext().build() in the Application class onCreate.");
    }

    public static int getInt(String argKey, int argDefValue) {
        return getPreferences().getInt(argKey, argDefValue);
    }

    public static int getInt(String argKey) {
        return getPreferences().getInt(argKey, 0);
    }

    public static void putInt(String argKey, int argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(argKey, argValue);
        editor.apply();
    }

    public static boolean getBoolean(String argKey, boolean argDefValue) {
        return getPreferences().getBoolean(argKey, argDefValue);
    }

    public static boolean getBoolean(String argKey) {
        return getPreferences().getBoolean(argKey, false);
    }

    public static void putBoolean(String argKey, boolean argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(argKey, argValue);
        editor.apply();
    }

    public static long getLong(String argKey, long argDefValue) {
        return getPreferences().getLong(argKey, argDefValue);
    }

    public static long getLong(String argKey) {
        return getPreferences().getLong(argKey, 0L);
    }

    public static void putLong(String argKey, long argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(argKey, argValue);
        editor.apply();
    }

    public static double getDouble(String argKey, double argDefValue) {
        return Double.longBitsToDouble(getPreferences().getLong(argKey, Double.doubleToLongBits(argDefValue)));
    }

    public static double getDouble(String argKey) {
        return Double.longBitsToDouble(getPreferences().getLong(argKey, Double.doubleToLongBits(0.0d)));
    }

    public static void putDouble(String argKey, double argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(argKey, Double.doubleToRawLongBits(argValue));
        editor.apply();
    }

    public static float getFloat(String argKey, float argDefValue) {
        return getPreferences().getFloat(argKey, argDefValue);
    }

    public static float getFloat(String argKey) {
        return getPreferences().getFloat(argKey, 0.0f);
    }

    public static void putFloat(String argKey, float argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(argKey, argValue);
        editor.apply();
    }

    public static String getString(String argKey, String argDefValue) {
        return getPreferences().getString(argKey, argDefValue);
    }

    public static String getString(String argKey) {
        return getPreferences().getString(argKey, null);
    }

    @SuppressWarnings("WeakerAccess")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String argKey, Set<String> argDefValue) {
        SharedPreferences prefs = getPreferences();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return prefs.getStringSet(argKey, argDefValue);
        } else {
            // Workaround for pre-HC's missing getStringSet
            return getOrderedStringSet(argKey, argDefValue);
        }
    }

    public static void putString(String argKey, String argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(argKey, argValue);
        editor.apply();
    }

    @SuppressWarnings("WeakerAccess")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void putStringSet(String argKey, Set<String> argValue) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SharedPreferences.Editor editor = getPreferences().edit();
            editor.putStringSet(argKey, argValue);
            editor.apply();
        } else {
            // Workaround for pre-HC's lack of StringSets
            putOrderedStringSet(argKey, argValue);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static Set<String> getOrderedStringSet(String argKey, final Set<String> argDefValue) {
        SharedPreferences prefs = getPreferences();
        if (prefs.contains(argKey + LENGTH)) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            int stringSetLength = prefs.getInt(argKey + LENGTH, -1);
            if (stringSetLength >= 0) {
                for (int i = 0; i < stringSetLength; i++) {
                    set.add(prefs.getString(argKey + "[" + i + "]", null));
                }
            }
            return set;
        }
        return argDefValue;
    }

    @SuppressWarnings("WeakerAccess")
    public static void putOrderedStringSet(String argKey, Set<String> argValue) {
        SharedPreferences.Editor editor = getPreferences().edit();
        int stringSetLength = 0;
        if (sharedPrefs.contains(argKey + LENGTH)) {
            // First read what the value was
            stringSetLength = sharedPrefs.getInt(argKey + LENGTH, -1);
        }
        editor.putInt(argKey + LENGTH, argValue.size());
        int i = 0;
        for (String aValue : argValue) {
            editor.putString(argKey + "[" + i + "]", aValue);
            i++;
        }
        for (; i < stringSetLength; i++) {
            // Remove any remaining values
            editor.remove(argKey + "[" + i + "]");
        }
        editor.apply();
    }

    public static void remove(String argKey) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.contains(argKey + LENGTH)) {
            // Workaround for pre-HC's lack of StringSets
            int stringSetLength = prefs.getInt(argKey + LENGTH, -1);
            if (stringSetLength >= 0) {
                editor.remove(argKey + LENGTH);
                for (int i = 0; i < stringSetLength; i++) {
                    editor.remove(argKey + "[" + i + "]");
                }
            }
        }
        editor.remove(argKey);
        editor.apply();
    }

    public static boolean contains(String argKey) {
        return getPreferences().contains(argKey);
    }

    public static SharedPreferences.Editor clear() {
        SharedPreferences.Editor editor = getPreferences().edit().clear();
        editor.apply();
        return editor;
    }

    public static SharedPreferences.Editor edit() {
        return getPreferences().edit();
    }

    public static class Builder {
        private Context context;
        private String prefsKey;
        private int prefsMode = -1;
        private boolean prefsUseDefault = false;

        public Builder withPrefsName(String argPrefsName) {
            prefsKey = argPrefsName;
            return this;
        }

        public Builder withContext(Context argContext) {
            context = argContext;
            return this;
        }

        @SuppressLint({"WorldReadableFiles", "WorldWriteableFiles"})
        public Builder withMode(Mode argMode) {
            if (argMode.label == ContextWrapper.MODE_PRIVATE || argMode.label == ContextWrapper.MODE_WORLD_READABLE || argMode.label == ContextWrapper.MODE_WORLD_WRITEABLE || argMode.label == ContextWrapper.MODE_MULTI_PROCESS) {
                prefsMode = argMode.label;
            } else {
                throw new RuntimeException("The mode in the SharedPreference can only be set too ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS");
            }
            return this;
        }

        @SuppressWarnings("SameParameterValue")
        public Builder withDefaultPrefs(boolean argDefaultPrefs) {
            prefsUseDefault = argDefaultPrefs;
            return this;
        }

        public ProPreferences build() {
            if (context == null) {
                throw new RuntimeException("Context not set, please set context before building the Prefs instance.");
            }

            if (TextUtils.isEmpty(prefsKey)) {
                prefsKey = context.getPackageName();
            }

            if (prefsUseDefault) {
                prefsKey += DEFAULT_SUFFIX;
            }

            if (prefsMode == -1) {
                prefsMode = Mode.PRIVATE.label;
            }

            //ProPreferencesManager.initPrefs(context, prefsKey, prefsMode);
            return new ProPreferences(this);
        }
    }

    public enum Mode {
        PRIVATE(ContextWrapper.MODE_PRIVATE),
        @SuppressLint("WorldReadableFiles")
        WORLD_READABLE(ContextWrapper.MODE_WORLD_READABLE),
        @SuppressLint("WorldWriteableFiles")
        WORLD_WRITEABLE(ContextWrapper.MODE_WORLD_WRITEABLE),
        MULTI_PROCESS(ContextWrapper.MODE_MULTI_PROCESS),
        DEFAULT(ContextWrapper.MODE_PRIVATE);
        public int label;

        private Mode(int label) {
            this.label = label;
        }

        public static Mode getMode(int argLabel) {
            for (Mode item : values()) {
                if (item.label == argLabel) {
                    return item;
                }
            }
            return DEFAULT;
        }
        /*public static Mode valueOfLabel(String label) {
            for (Mode e : values()) {
                if (e.label.equals(label)) {
                    return e;
                }
            }
            return null;
        }*/
    }
}

/**
 * ProPreferences class usages
 * Java:
 * <p>
 * Step 1:
 * ProPreferences proPreferences;
 * <p>
 * Step 2:
 * proPreferences = new ProPreferences.Builder()
 * .withContext(context)
 * .withMode(ProPreferences.Mode.PRIVATE)
 * .withPrefsName(getPackageName())
 * .withDefaultPrefs(true)
 * .build();
 * <p>
 * Step 2: put value in preferences;
 * ProPreferences.putString(key, string);
 * ProPreferences.putLong(key, long);
 * ProPreferences.putBoolean(key, boolean);
 * <p>
 * Step 3: get value from preferences
 * String data = ProPreferences.getString(key, default value);
 * <p>
 * Advanced feature:
 * void ProPreferences.putOrderedStringSet(String key, Set<String> value);
 * Set<String> ProPreferences.getOrderedStringSet(String key, Set<String> defaultValue);
 */
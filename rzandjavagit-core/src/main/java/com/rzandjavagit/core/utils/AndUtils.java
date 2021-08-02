package com.rzandjavagit.core.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class AndUtils {
    //|------------------------------------------------------------|
    public static boolean isBuildDebug(Context argContext) {
        return 0 != (argContext.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE);
    }

    //|------------------------------------------------------------|
    //@SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        if (html == null) {
            return new SpannableString("");
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(html);
        }
    }

    //|------------------------------------------------------------|
    public static class OnColorFilter {
        public static Drawable getToolBarIconDrawable(Toolbar argToolBar) {
            return argToolBar.getNavigationIcon();
        }

        public static void setColorFilter(@NonNull Drawable argDrawable, int argColor) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                argDrawable.setColorFilter(new BlendModeColorFilter(argColor, BlendMode.SRC_ATOP));
            } else {
                argDrawable.setColorFilter(argColor, PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
    //|------------------------------------------------------------|
}

package com.rzandjavagit.prospotlightview.utils;

import android.content.res.Resources;

/**
 * Created 2017-10-13
 */

public class Utils {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}

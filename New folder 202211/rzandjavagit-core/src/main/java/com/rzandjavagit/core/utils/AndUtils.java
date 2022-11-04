package com.rzandjavagit.core.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;

public class AndUtils {
    public static Spanned fromHtml(String argValue) {
        if (isNullOrEmpty(argValue)) {
            return null;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(argValue, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(argValue);
        }
    }

    public static int getDrawableId(Context argContext, String argName) {
        Resources resources = argContext.getResources();
        int resourceId = resources.getIdentifier(argName, "drawable", argContext.getPackageName());
        return resourceId;
    }

    public static Drawable getDrawable(Context argContext, String argName) {
        Resources resources = argContext.getResources();
        int resourceId = resources.getIdentifier(argName, "drawable", argContext.getPackageName());
        return resources.getDrawable(resourceId);
    }

    public static int getRawId(Context argContext, String argName) {
        Resources resources = argContext.getResources();
        int resourceId = resources.getIdentifier(argName, "raw", argContext.getPackageName());
        return resourceId;
    }

    private static boolean isNullOrEmpty(String argValue) {
        if (argValue == null) {
            return true;
        }
        if (argValue.trim().isEmpty()) {
            return true;
        }
        if (argValue.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static Bitmap getDrawableToBitmap(Context argContext, int argResourceId) {
        Resources resources = argContext.getResources();
        return BitmapFactory.decodeResource(resources, argResourceId);
    }

    public static Bitmap getDrawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}

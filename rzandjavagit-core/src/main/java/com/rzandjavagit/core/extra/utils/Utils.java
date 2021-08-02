package com.rzandjavagit.core.extra.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Utils.java - a simple class for demonstrating the use of javadoc comments.
 *
 * @author Rz Rasel
 * @version 100.00.01
 * @see Object
 */
public class Utils {
    @Deprecated
    public static String getSecondToDateTime(long argTimeInSecond, String argInFormat) {
        Date date = new Date(argTimeInSecond * 1000);
        //Date date = new Date(getCurrentSecond() * 1000);
        //DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat(argInFormat);
        //System.out.println("----------------->" + dateFormat.format(date));
        return dateFormat.format(date);
    }

    @Deprecated
    public static String getGMTSecondToDateTime(long argTimeInSecond, String argInFormat) {
        Date date = new Date(argTimeInSecond * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(argInFormat);
        // Give it to me in GMT time.
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println("GMT time: " + simpleDateFormat.format(date));
        return simpleDateFormat.format(date);
    }

    @Deprecated
    public static long getSecond(String argDateTime, String argInFormat) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(argInFormat);
        try {
            //Date date = simpleDateFormat.parse(simpleDateFormat.format(argDateTime));
            Date date = simpleDateFormat.parse(argDateTime);
            return date.getTime() / 1000;
        } catch (ParseException ex) {
            //ex.printStackTrace();
            return System.currentTimeMillis() / 1000;
        }
    }

    @Deprecated
    public static long getGMTSecond(String argDateTime, String argInFormat) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(argInFormat, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            //Date date = simpleDateFormat.parse(simpleDateFormat.format(argDateTime));
            DateFormat dateFormat = new SimpleDateFormat(argInFormat);
            Date date = simpleDateFormat.parse(argDateTime);
            return date.getTime() / 1000;
        } catch (ParseException ex) {
            //ex.printStackTrace();
            return System.currentTimeMillis() / 1000;
        }
    }

    @Deprecated
    public static long getCurrentSecond() {
        return System.currentTimeMillis() / 1000;
    }

    @Deprecated
    public static long getCurrentGMTSecond() {
        long millisSecond = System.currentTimeMillis();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        // Give it to me in GMT time.
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //String dateStr = simpleDateFormat.format(date);
        try {
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            return date.getTime() / 1000;
        } catch (ParseException ex) {
            //ex.printStackTrace();
            return System.currentTimeMillis() / 1000;
        }
    }

    public enum DATE_FORMAT {
        YYYYMMDDHHMMSS("yyyy-MM-dd HH:mm:ss");
        private final String item;

        DATE_FORMAT(String argItem) {
            this.item = argItem;
        }

        public String getItem() {
            return this.item;
        }
    }

    @Deprecated
    public static String getGMTToLocalTime(String argDateTime, String argInFormat, String argOutFormat) {
        /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy . hh:mm aa");*/
        //DateFormat dateFormat = new SimpleDateFormat(argInFormat, Locale.getDefault());
        DateFormat dateFormat = new SimpleDateFormat(argInFormat, Locale.getDefault());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(argOutFormat);
        //simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Locale));
        //simpleDateFormat.setTimeZone(TimeZone.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        //System.out.println("-----+ " + TimeZone.getDefault());
        try {
            Date date = dateFormat.parse(argDateTime);
            return simpleDateFormat.format(date);
        } catch (ParseException ex) {
            //ex.printStackTrace();
            return null;
        }
        //https://medium.com/@kosta.palash/converting-date-time-considering-time-zone-android-b389ff9d5c49
        //https://www.tutorialspoint.com/java/util/calendar_settimezone.htm
        //writeDate.setTimeZone(TimeZone.getTimeZone("GMT+04:00"));
        //https://vectr.com/tmp/a1hCr4Gvwc/aaegiGLyoI
        //http://inloop.github.io/svg2android/
    }

    /**
     * Retrieve the value is null or empty.
     *
     * @param argValue A variable of type String.
     * @return A boolean data type.
     */

    public static Spanned fromHtml(String argValue) {
        if (com.rzandjavagit.core.utils.Utils.isNullOrEmpty(argValue)) {
            return null;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            return Html.fromHtml(argValue, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(argValue);
        }
    }

    /**
     * Function used to fetch an XML file from assets folder
     *
     * @param argContext - Context
     * @param argName    - XML file name to convert it to String
     * @return - return Drawable ID in int form
     */
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
}
/*
// Encode data on your side using BASE64
byte[] bytesEncoded = Base64.encodeBase64(str.getBytes());
System.out.println("encoded value is " + new String(bytesEncoded));

// Decode data on other side, by processing encoded data
byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
System.out.println("Decoded value is " + new String(valueDecoded));
*/
/*
InputStream in_s = getApplicationContext().getAssets().open("www/application/app/client/controllers/data1.xml");
*/
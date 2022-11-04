package com.rzandjavagit.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <h1>C0367a</h1>
 * <p>
 * Use for object memory cache
 * </p>
 *
 * @author Rz Rasel (Md. Rashed - Uz - Zaman)
 * @version 100.00.01
 * @since 2018-12-10
 */

class C0367a {
    private static final String[] f1106a = new String[]{"dd/MM/yyyy", "dd/MM/yyyy hh:mm:ss", "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy'T'HH:mm:ss.SSS'Z'", "dd/MM/yyyy'T'HH:mm:ss.SSSZ", "dd/MM/yyyy'T'HH:mm:ss.SSS", "dd/MM/yyyy'T'HH:mm:ssZ", "dd/MM/yyyy'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd hh:mm:ss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss'Z'", "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "MM/dd/yyyy", "MM/dd/yyyy hh:mm:ss", "MM/dd/yyyy HH:mm:ss", "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS", "MM/dd/yyyy'T'HH:mm:ssZ", "MM/dd/yyyy'T'HH:mm:ss", "yyyy:MM:dd HH:mm:ss", "yyyyMMdd"};

    protected static String m1386a(String str, String str2) throws ParseException, NullPointerException {
        if (m1388a(str) || m1388a(str2)) {
            throw new NullPointerException();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            throw e;
        }
    }

    protected static String m1387a(String str, String str2, String str3) throws ParseException, NullPointerException {
        if (m1388a(str) || m1388a(str2) || m1388a(str3)) {
            throw new NullPointerException();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            return new SimpleDateFormat(str3).format(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            throw e;
        }
    }

    protected static boolean m1389b(String str, String str2) {
        if (m1388a(str) || m1388a(str2)) {
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            simpleDateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getSecondToDateTime(long argTimeInSecond, String argInFormat) {
        Date date = new Date(argTimeInSecond * 1000);
        //Date date = new Date(getCurrentSecond() * 1000);
        //DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat(argInFormat);
        //System.out.println("----------------->" + dateFormat.format(date));
        return dateFormat.format(date);
    }

    public static String getGMTSecondToDateTime(long argTimeInSecond, String argInFormat) {
        Date date = new Date(argTimeInSecond * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(argInFormat);
        // Give it to me in GMT time.
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println("GMT time: " + simpleDateFormat.format(date));
        return simpleDateFormat.format(date);
    }

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

    public static long getCurrentSecond() {
        return System.currentTimeMillis() / 1000;
    }

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

    private static boolean m1388a(String str) {
        if (str == null) {
            return true;
        }
        str = str.replaceAll("\\s+", "");
        return str.trim().isEmpty() || str.equalsIgnoreCase("null");
    }
}

package com.rzandjavagit.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    public static String getFormattedDate(String argStrDate, String argInDateFormat) throws ParseException, NullPointerException {
        return C0367a.m1386a(argStrDate, argInDateFormat);
    }

    public static String getFormattedDate(String argStrDate, String argInDateFormat, String argOutDateFormat) throws ParseException, NullPointerException {
        return C0367a.m1387a(argStrDate, argInDateFormat, argOutDateFormat);
    }

    public static boolean isValidDate(String argDateString, String argDateFormat) {
        return C0367a.m1389b(argDateString, argDateFormat);
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
    }
}
//https://vectr.com/tmp/a1hCr4Gvwc/aaegiGLyoI
//http://inloop.github.io/svg2android/
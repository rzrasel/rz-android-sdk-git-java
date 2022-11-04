package com.rzandjavagit.proadmob;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

class AdMobUtils {
    public static int getRandomId(int from, int to) {
        Random random = new Random();
        return random.nextInt((to - from) + 1) + from;
    }

    public static double getRandomDouble(double from, double to) {
        Random random = new Random();
        return random.nextDouble() * (to - from) + from;
    }

    public static double getDecimalFormat(double value) {
        /*String pattern = "#.##";
        NumberFormat decimalFormat = new DecimalFormat(pattern);
        return Double.parseDouble(decimalFormat.format(value));*/
        //return Double.parseDouble(String.format("%.2f", value));
        return value;
    }

    public static float getRandomFloat(float from, float to) {
        Random random = new Random();
        return random.nextFloat() * (to - from) + from;
    }

    public static float getDecimalFormat(float value) {
        /*String pattern = "#.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return Float.parseFloat(decimalFormat.format(value));*/
        //return Float.parseFloat(String.format("%.2f", value));
        return value;
    }
}

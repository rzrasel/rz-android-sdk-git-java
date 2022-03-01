package com.rzandjavagit.proadmob;

import java.text.DecimalFormat;
import java.util.Random;

class AdMobUtils {
    public static int getRandomId(int from, int to) {
        Random random = new Random();
        return random.nextInt((to - from) + 1) + from;
    }

    public static float getRandomFloat(float from, float to) {
        Random random = new Random();
        return getDecimalFormat(random.nextFloat() * (to - from) + from);
    }

    public static float getDecimalFormat(float value) {
        String pattern = "#.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return Float.parseFloat(decimalFormat.format(value));
    }
}

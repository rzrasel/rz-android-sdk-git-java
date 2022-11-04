package com.rzandjavagit.core.extra.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IsValidateUtils {

    public static boolean isJson(String argJson) {
        try {
            new JSONObject(argJson);
        } catch (JSONException ex) {
            try {
                new JSONArray(argJson);
            } catch (JSONException exc) {
                return false;
            }
        }
        return true;
    }

    //if (containsEnum(PRIORITY.class, strMethodName)) {
    public static <T extends Enum<T>> boolean isContainsEnum(Class<T> argEnum, String argValue) {
        if (!argEnum.isEnum()) {
            return false;
        }
        for (Enum<T> item : argEnum.getEnumConstants()) {
            if (item.toString().equals(argValue)) {
                return true;
            }
        }
        return false;
    }

    public static <E extends Enum<E>> boolean containsEnum(Class<E> argEnum, String argValue) {
        if (!argEnum.isEnum()) {
            return false;
        }
        for (Enum<E> item : argEnum.getEnumConstants()) {
            if (item.toString().equals(argValue)) {
                return true;
            }
        }
        return false;
        //if(Utils.isContainsEnum(NavDrawerMenuTypes.MENU_POSITION.class, "FIRST"))
        //System.out.println(Utils.isContainsEnum(NavDrawerMenuTypes.MENU_POSITION.class, "FIRST"));
    }
}

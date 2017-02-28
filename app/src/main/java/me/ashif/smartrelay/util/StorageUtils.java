package me.ashif.smartrelay.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by asif
 */
public class StorageUtils {
    private static final String SMARTRELAY_USER_PREF = "SMARTRELAY_USER_PREF";

    public static void storeString(String name, String data, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, data);
        editor.apply();
    }

    public static String fetchString(String name, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        return preferences.getString(name, "");
    }

    public static void storeInt(String name, int data, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, data);
        editor.apply();
    }

    public static int fetchInt(String name, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        return preferences.getInt(name, -1);
    }

    public static void storeBoolean(String name, boolean data, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(name, data);
        editor.apply();
    }

    public static boolean fetchBoolean(String name, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        return preferences.getBoolean(name, false);
    }

    public static void removeKey(String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        preferences.edit().remove(key).apply();
    }

    public static void clearUserPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SMARTRELAY_USER_PREF, Context.MODE_PRIVATE);
        preferences.edit().clear().apply();
    }
}

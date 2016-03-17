package com.upyun.tvplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    public static String FILE_NAME;
    public static String CHANNEL = "channel";
    public static String URL = "url";

    public static void saveChannel(Context context, int id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CHANNEL, id);
        editor.commit();
    }

    public static int getChannel(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        //默认频道100
        return sharedPreferences.getInt(CHANNEL, 100);
    }

    public static void saveURL(Context context, String url) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(URL, url);
        editor.commit();
    }

    public static String getURL(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(URL, null);
    }
}

package com.upyun.tvplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    public static String FILE_NAME;
    public static String CHANNEL_ID = "channelId";
    public static String URL = "url";

    public static void saveChannelId(Context context, int id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CHANNEL_ID, id);
        editor.commit();
    }

    public static int getChannelId(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
        //默认频道100
        return sharedPreferences.getInt(CHANNEL_ID, 100);
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

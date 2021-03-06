package com.upyun.tvplayer.util;

import android.app.Application;
import android.util.Log;

import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.Channel;
import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.model.ProgramList;

import java.util.List;

public class MyApplication extends Application {
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<com.upyun.tvplayer.model.Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<com.upyun.tvplayer.model.Program> programs) {
        this.programs = programs;
    }

    private List<Category> categories;
    private List<Program> programs;

    public static ProgramList programList;
    public static Channel channel;

    @Override
    public void onCreate() {
        super.onCreate();
        channel = LocalSave.getChannel(getApplicationContext());
        programList = LocalSave.getProgram(getApplicationContext());
        Log.e("hahaah", (channel != null) + ":::" + (programList != null));
    }
}

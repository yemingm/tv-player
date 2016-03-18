package com.upyun.tvplayer.util;

import android.app.Application;

import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.Program;

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
}

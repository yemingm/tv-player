package com.upyun.tvplayer.net;

import com.google.gson.Gson;
import com.upyun.tvplayer.Exception.UpYunException;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Category;

import okhttp3.Call;

public class CategoryAPI extends BaseAPI<Category[]> {

    public CategoryAPI() {
        super();
    }

    @Override
    Category[] onResult(Call call, String data) throws UpYunException {
        Gson gson = new Gson();
        Category[] categories = gson.fromJson(data, Category[].class);
        return categories;
    }

    public Call getCategory(UIListener<Category[]> uiListener) {
        String url = HOST + category + "?api_key=" + KEY;
        return get(uiListener, url);
    }
}

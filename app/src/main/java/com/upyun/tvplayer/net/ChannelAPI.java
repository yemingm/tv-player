package com.upyun.tvplayer.net;

import android.util.Log;

import com.google.gson.Gson;
import com.upyun.tvplayer.Exception.UpYunException;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.ChannelList;

import okhttp3.Call;

public class ChannelAPI extends BaseAPI<ChannelList> {

    private static final String TAG = "ChannelAPI";

    public ChannelAPI() {
        super();
    }

    @Override
    ChannelList onResult(Call call, String data) throws UpYunException {
        Log.e(TAG, "haha"+data);
        Gson gson = new Gson();
        ChannelList channels = gson.fromJson(data, ChannelList.class);
        return channels;
    }

    public Call getChannels(UIListener<ChannelList> uiListener, int categoryID) {
        String url = HOST + channels + "?api_key=" + KEY + "&broadcast_category=" + categoryID+"&start=0&count=1000";
        return get(uiListener, url);
    }
}

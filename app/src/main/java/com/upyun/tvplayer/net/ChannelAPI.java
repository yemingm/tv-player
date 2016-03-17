package com.upyun.tvplayer.net;

import com.google.gson.Gson;
import com.upyun.tvplayer.Exception.UpYunException;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.ChannelData;

import okhttp3.Call;

public class ChannelAPI extends BaseAPI<ChannelData> {

    private static final String TAG = "ChannelAPI";

    public ChannelAPI() {
        super();
    }

    @Override
    ChannelData onResult(Call call, String data) throws UpYunException {
//        Log.e(TAG, data);
        Gson gson = new Gson();
        ChannelData channels = gson.fromJson(data, ChannelData.class);
        return channels;
    }

    public Call getChannels(UIListener<ChannelData> uiListener, int categoryID) {
        String url = HOST + channels + "?api_key=" + KEY + "&broadcast_category=" + categoryID+"&start=0&count=1000";
        return get(uiListener, url);
    }

    //  返回json格式不对
//    public Call getChannelById(UIListener<ChannelData> uiListener, int id) {
//        String url = HOST + channels + "?api_key=" + KEY + "&id=" + id+"&start=0&count=1000";
//        return get(uiListener, url);
//    }
}

package com.upyun.tvplayer.net;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upyun.tvplayer.Exception.RespException;
import com.upyun.tvplayer.Exception.UpYunException;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.util.AsyncRun;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

abstract class BaseAPI<T> {
    private static final String TAG = "BaseAPI";
    protected String HOST = "http://dev.chnvideo.com:1994";
    protected String KEY = "388261d5637042bbf6e7c3e8c0d6c284";
    protected String category = "/api/v1/broadcast_category";
    protected String channels = "/api/v1/channels";
    protected String program = "/api/v1/programs/wprograms";
    protected OkHttpClient client;

    protected BaseAPI() {
        client = new OkHttpClient();
    }

    protected Call get(UIListener uiListener, String url) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(asyncCall(uiListener));
        return call;
    }

    protected Callback asyncCall(final UIListener uiListener) {
        return new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                AsyncRun.run(new Runnable() {
                    @Override
                    public void run() {
                        uiListener.onSuccessed(e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                final String resp = response.body().string();
                AsyncRun.run(new Runnable() {
                    @Override
                    public void run() {
                        int code = -1;
                        String data = null;
                        if (!response.isSuccessful()) {
                            uiListener.onfailed(new RespException(resp, response.code()));
                        } else {
                            JsonObject object = new JsonParser().parse(resp).getAsJsonObject();
                            code = object.get("code").getAsInt();
                            data = object.get("data").toString();
                        }
                        if (code != 0) {
                            uiListener.onfailed(new UpYunException("wrong code:" + code));
                        } else {
                            T result = null;
                            try {
                                result = onResult(call, data);
                            } catch (UpYunException e) {
                                uiListener.onfailed(e);
                            }
                            uiListener.onSuccessed(result);
                        }
                    }
                });
            }
        };
    }

    abstract T onResult(Call call, String data) throws UpYunException;
}

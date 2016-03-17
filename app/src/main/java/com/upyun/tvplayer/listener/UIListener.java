package com.upyun.tvplayer.listener;

public interface UIListener<T> {
    void onSucceed(T result);

    void onFailed(Exception e);
}

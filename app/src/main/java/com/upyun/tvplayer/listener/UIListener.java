package com.upyun.tvplayer.listener;

public interface UIListener<T> {
    void onSuccessed(T result);

    void onfailed(Exception e);
}

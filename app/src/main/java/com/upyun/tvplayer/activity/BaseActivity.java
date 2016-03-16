package com.upyun.tvplayer.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView();
        loadDate();
    }

    protected abstract void initVariables();

    protected abstract void loadDate();

    protected abstract void initView();

}

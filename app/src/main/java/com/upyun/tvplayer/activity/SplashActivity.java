package com.upyun.tvplayer.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.net.CategoryAPI;
import com.upyun.tvplayer.net.ProgramAPI;
import com.upyun.tvplayer.util.MyApplication;
import com.upyun.tvplayer.util.SharedPreferencesUtils;

import java.util.Arrays;
import java.util.List;

public class SplashActivity extends BaseActivity implements UIListener {

    private static final String TAG = "SplashActivity";
    ImageView mIvBackground;
    private boolean isCategoryGet;
    private boolean isProgramGet;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void loadDate() {
        CategoryAPI categoryAPI = new CategoryAPI();
        categoryAPI.getCategory(this);
        ProgramAPI channelAPI = new ProgramAPI();
        channelAPI.getProgram(this, ProgramAPI.Week.this_week, ProgramAPI.WeekDay.Sun, SharedPreferencesUtils.getChannelId(this));
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        mIvBackground = (ImageView) findViewById(R.id.iv_splash);
        mIvBackground.setImageResource(R.drawable.splash_background);
    }

    @Override
    public void onSucceed(Object result) {
        Intent intent = new Intent(SplashActivity.this, TVPlayerActivity.class);

        if (result instanceof Category[]) {
            List<Category> categories = Arrays.asList((Category[]) result);
//            intent.putExtra("categories", (Serializable) categories);
            ((MyApplication)getApplication()).setCategories(categories);
            Log.e(TAG, categories.toString());
            isCategoryGet = true;
        } else if (result instanceof Program[]) {
            List<Program> programs = Arrays.asList((Program[]) result);
//            intent.putExtra("programs", (Serializable) programs);
            ((MyApplication)getApplication()).setPrograms(programs);
            Log.e(TAG, programs.toString());
            isProgramGet = true;
        }

        if (isCategoryGet && isProgramGet) {
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(SplashActivity.this, "初始化数据失败", Toast.LENGTH_LONG).show();
        mIvBackground.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }
}

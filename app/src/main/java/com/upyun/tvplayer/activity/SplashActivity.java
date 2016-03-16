package com.upyun.tvplayer.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.net.CategoryAPI;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";
    Category[] mCategories;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void loadDate() {
        CategoryAPI categoryAPI = new CategoryAPI();
        categoryAPI.getCategory(new UIListener<Category[]>() {
            @Override
            public void onSuccessed(Category[] result) {
                final Intent intent = new Intent(SplashActivity.this, TVPlayerActivity.class);
                List<Category> categories = Arrays.asList(result);
                intent.putExtra("categories", (Serializable) categories);
                Log.e(TAG, categories.toString());
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        startActivity(intent);
//                        finish();
//                    }
//                };
//                Timer timer = new Timer();
//                timer.schedule(timerTask, 3000);

                startActivity(intent);
                finish();

            }

            @Override
            public void onfailed(Exception e) {
                Log.e(TAG, "获取频道分类失败");
            }

        });
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView) findViewById(R.id.iv_splash);
        imageView.setImageResource(R.drawable.splash_background);
    }
}

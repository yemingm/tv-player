package com.upyun.tvplayer.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.upyun.tvplayer.R;


public class InfoView extends RelativeLayout {

    private TextView titleView, itemView, targetView;
    private ProgressBar progressBar;

    public void setItemText(CharSequence text) {
        itemView.setText(text);
    }

    public void setTitleText(CharSequence text) {
        titleView.setText(text);
    }

    public void setTargetText(CharSequence text) {
        targetView.setText("即将播放：" + text);
    }

    public void setProgress(int progress) {
        progressBar.setProgress(progress);

    }

    public void setMaxProgress(int max) {
        progressBar.setMax(max);
    }

    public InfoView(Context context) {
        super(context);
    }

    public void show() {
        this.setVisibility(VISIBLE);
        handler.removeMessages(REMOVE_INFO);
        handler.sendEmptyMessageDelayed(REMOVE_INFO, 5000);
    }

    private final int REMOVE_INFO = 10010;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REMOVE_INFO:
                    setVisibility(INVISIBLE);
            }

        }
    };

    public InfoView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_info, this);
        titleView = (TextView) findViewById(R.id.tv_title);
        itemView = (TextView) findViewById(R.id.tv_item);
        targetView = (TextView) findViewById(R.id.tv_target);
        progressBar = (ProgressBar) findViewById(R.id.pb_info);
        progressBar.setProgress(20);
        setVisibility(INVISIBLE);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InfoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public InfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

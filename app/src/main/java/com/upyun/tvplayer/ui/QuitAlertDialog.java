package com.upyun.tvplayer.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.view.Window;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.util.LocalSave;
import com.upyun.tvplayer.util.MyApplication;

public class QuitAlertDialog extends Dialog {
    public QuitAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_quit_alert);
        initViews();
    }

    public void initViews() {
        findViewById(R.id.quitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuitAlertDialog.this.dismiss();
                LocalSave.saveChannel(getContext(),MyApplication.channel);
                LocalSave.saveProgram(getContext(),MyApplication.programList);
                android.os.Process.killProcess(Process.myPid());
            }
        });
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuitAlertDialog.this.dismiss();
            }
        });
    }

    public static void show(Context context) {
        new QuitAlertDialog(context).show();
    }
}

package com.upyun.tvplayer.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.*;
import android.os.Process;
import android.view.View;

import com.upyun.tvplayer.R;

public class QuitAlertDialog extends Dialog {
    public QuitAlertDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_quit_alert);
        initViews();
    }

    public void initViews() {
        findViewById(R.id.quitButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                QuitAlertDialog.this.dismiss();
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

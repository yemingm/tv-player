package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upyun.tvplayer.model.Program;

public class ProgramFragAdapter extends BaseAdapter {

    private Program program;
    private Context mContext;

    public ProgramFragAdapter(Program channelList, Context context) {
        this.program = channelList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
//        if(program!=null){
//            return program.getProgramList().size();
//        }
//        return 0;

        return 10;
    }

    @Override
    public Object getItem(int position) {
        return program.getProgramList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
//        textView.setText( program.getProgramList().get(position).getProgramName());
        textView.setText( "精彩节目");
        textView.setTextSize(30);
        return textView;
    }
}

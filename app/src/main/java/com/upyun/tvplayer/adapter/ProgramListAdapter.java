package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.model.ProgramList;

public class ProgramListAdapter extends BaseAdapter {

    private Program mProgram;
    private Context mContext;

    public ProgramListAdapter(Program program, Context context) {
        this.mProgram = program;
        this.mContext = context;
    }

    @Override
    public int getCount() {
//        if(mProgram==null||mProgram.getProgramList()==null) {
//            return 0;
//        }
//        return mProgram.getProgramList().size();
        return 10;
    }

    @Override
    public ProgramList getItem(int position) {
        //TODO 没有真实数据 模拟数据
 //        return mProgram.getProgramList().get(position);
        ProgramList programList = new ProgramList();
        programList.setProgramName("精彩节目:"+position);
        return programList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ProgramList programList = getItem(position);

        TextView textView = new TextView(mContext);
//        textView.setText( mProgram.getProgramList().get(position).getProgramName());
        textView.setText(programList.getProgramName());
        textView.setTextSize(30);
        return textView;
    }
}

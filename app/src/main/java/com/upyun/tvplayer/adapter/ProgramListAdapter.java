package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.model.ProgramList;
import com.upyun.tvplayer.util.MyApplication;

import java.util.ArrayList;

public class ProgramListAdapter extends BaseAdapter {

    private Program mProgram;
    private Context mContext;

    public ProgramListAdapter(Program program, Context context) {
        this.mProgram = program;
        this.mContext = context;

        //TODO 没有真实数据 模拟数据
        //        return mProgram.getProgramList().get(position);
        mProgram = new Program();
        mProgram.setProgramList(new ArrayList<ProgramList>());
        for (int i = 0; i < getCount(); i++) {
            mProgram.getProgramList().add(new ProgramList());
        }

    }

    @Override
    public int getCount() {
        //TODO
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
        ProgramList programList = mProgram.getProgramList().get(position);
        programList.setProgramName("节目名字" + position);
        programList.setStartTime(position);
        return programList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //TODO listview 设计
        ProgramList programList = getItem(position);

        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_program, null, false);
            holder.playIcon = (ImageView) convertView.findViewById(R.id.iv_program_icon);
            holder.tvProgramTime = (TextView) convertView.findViewById(R.id.tv_program_time);
            holder.tvProgramName = (TextView) convertView.findViewById(R.id.tv_program_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (MyApplication.programList != null && MyApplication.programList.getProgramName().endsWith(programList.getProgramName()) && MyApplication.programList.getStartTime() == programList.getStartTime()) {
            holder.playIcon.setVisibility(View.VISIBLE);
        } else {
            holder.playIcon.setVisibility(View.INVISIBLE);
        }

        holder.tvProgramName.setText(programList.getProgramName());
        //模拟数据
        String time = 24 * (position + 1) / getCount() < 10 ? "0" + 24 * (position + 1) / getCount() + ":00" : 24 * (position + 1) / getCount() + ":00";
        holder.tvProgramTime.setText(time);
        return convertView;
    }


    class ViewHolder {
        ImageView playIcon;
        TextView tvProgramTime;
        TextView tvProgramName;
    }
}

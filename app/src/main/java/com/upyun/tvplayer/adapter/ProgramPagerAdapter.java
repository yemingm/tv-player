package com.upyun.tvplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.ui.ProgramFragment;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

public class ProgramPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {


    protected static final String[] CONTENT = new String[]{"this_Mon", "this_Tue", "this_Wed", "this_Thu", "this_Fri", "this_Sat", "this_Sun", "next_Mon", "next_Tue", "next_Wed", "next_Thu", "next_Fri", "next_Sat", "next_Sun"};
    protected static final int[] ICONS = new int[]{
            R.drawable.cover
    };

    private List<Program> mPrograms;

    public ProgramPagerAdapter(FragmentManager fm, List<Program> programs) {
        super(fm);
        this.mPrograms = programs;
    }

    @Override
    public Fragment getItem(int position) {
        //TODO
        //模拟数据
//        return new ProgramFragment(mPrograms.get(position));
        return new ProgramFragment(new Program());
    }

    @Override
    public int getCount() {
        //TODO
//        return mPrograms.size();
        return CONTENT.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //TODO
        //模拟数据
//        SimpleDateFormat format = new SimpleDateFormat("mm-dd");
//        Date date = new Date(mPrograms.get(position).getZeroTimestamp());
//        return format.format(date);
        return ProgramPagerAdapter.CONTENT[position % CONTENT.length];
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[0];
    }
}

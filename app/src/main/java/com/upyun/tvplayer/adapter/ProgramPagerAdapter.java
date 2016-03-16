package com.upyun.tvplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.ui.ProgramFragment;
import com.viewpagerindicator.IconPagerAdapter;

public class ProgramPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {


    protected static final String[] CONTENT = new String[]{"this_Mon", "this_Tue", "this_Wed", "this_Thu", "this_Fri", "this_Sat", "this_Sun", "next_Mon", "next_Tue", "next_Wed", "next_Thu", "next_Fri", "next_Sat", "next_Sun"};
    protected static final int[] ICONS = new int[]{
            R.drawable.cover
    };

    private int mCount = CONTENT.length;

    public ProgramPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
//        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
        return ProgramFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ProgramPagerAdapter.CONTENT[position % CONTENT.length];
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[0];
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}

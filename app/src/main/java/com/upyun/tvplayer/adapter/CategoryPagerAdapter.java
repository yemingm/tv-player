package com.upyun.tvplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.ui.ChannelFragment;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.List;

public class CategoryPagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private List<Category> mCategories;

    public CategoryPagerAdapter(FragmentManager fm, List<Category> categories) {
        super(fm);
        this.mCategories = categories;
    }

    protected static final int[] ICONS = new int[]{
            R.drawable.cover
    };

    @Override
    public Fragment getItem(int position) {
        return ChannelFragment.newInstance(mCategories.get(position));
    }

    @Override
    public int getCount() {
        if(mCategories !=null) {
            return mCategories.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategories.get(position).getName();
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[0];
    }
}

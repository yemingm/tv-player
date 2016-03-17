package com.upyun.tvplayer.adapter;

import android.content.Context;
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
    private Context mContext;

    public CategoryPagerAdapter(FragmentManager fm, List<Category> categories, Context context) {
        super(fm);
        this.mCategories = categories;
        this.mContext = context;
    }


    protected static final String[] CONTENT = new String[]{"This", "Is", "A", "Test",};
    protected static final int[] ICONS = new int[]{
            R.drawable.cover
    };

//    private int mCount = CONTENT.length;

//    public CategoryPagerAdapter(FragmentManager fm) {
//        super(fm);
//    }

    @Override
    public Fragment getItem(int position) {
//        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
//        ChannelFragment fragment = new ChannelFragment();
//        fragment.setCategory(mCategories.get(position));
//        return fragment;
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
//        return CategoryPagerAdapter.CONTENT[position % CONTENT.length];
        return mCategories.get(position).getName();
    }

    @Override
    public int getIconResId(int index) {
        return ICONS[0];
    }

//    public void setCount(int count) {
//        if (count > 0 && count <= 10) {
//            mCount = count;
//            notifyDataSetChanged();
//        }
//    }
}

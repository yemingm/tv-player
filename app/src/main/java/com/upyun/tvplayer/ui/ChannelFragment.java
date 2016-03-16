package com.upyun.tvplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.adapter.ChannelFragAdapter;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.ChannelList;
import com.upyun.tvplayer.net.ChannelAPI;

public class ChannelFragment extends Fragment {

    private Category mCategory;
    private ChannelFragAdapter mChannelFragAdapter;

    public static ChannelFragment newInstance(Category Category) {
        ChannelFragment fragment = new ChannelFragment();
        fragment.setmCategory(Category);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ListView lvChannel = (ListView) view.findViewById(R.id.lv_channel);
        mChannelFragAdapter = new ChannelFragAdapter(null, getContext());
        lvChannel.setAdapter(mChannelFragAdapter);
        loadDate();
    }

    private void loadDate() {
        ChannelAPI channelAPI = new ChannelAPI();
        channelAPI.getChannels(new UIListener<ChannelList>() {
            @Override
            public void onSuccessed(ChannelList result) {
                mChannelFragAdapter.setChannelList(result);
                mChannelFragAdapter.notifyDataSetChanged();
            }

            @Override
            public void onfailed(Exception e) {

            }
        }, mCategory.getId());
    }

    public Category getmCategory() {
        return mCategory;
    }

    public void setmCategory(Category mCategory) {
        this.mCategory = mCategory;
    }
}

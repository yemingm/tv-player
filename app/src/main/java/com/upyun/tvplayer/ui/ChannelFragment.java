package com.upyun.tvplayer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.upyun.tvplayer.R;
import com.upyun.tvplayer.adapter.ChannelListAdapter;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.ChannelData;
import com.upyun.tvplayer.net.ChannelAPI;

import org.greenrobot.eventbus.EventBus;

public class ChannelFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "ChannelFragment";
    private Category mCategory;
    private ChannelListAdapter mChannelListAdapter;

    public static ChannelFragment newInstance(Category Category) {
        ChannelFragment fragment = new ChannelFragment();
        fragment.setCategory(Category);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        ListView lvChannel = (ListView) view.findViewById(R.id.lv_channel);
        mChannelListAdapter = new ChannelListAdapter(null, getContext());
        lvChannel.setAdapter(mChannelListAdapter);
        lvChannel.setOnItemClickListener(this);
        loadDate();
    }

    private void loadDate() {
        ChannelAPI channelAPI = new ChannelAPI();
        channelAPI.getChannels(new UIListener<ChannelData>() {
            @Override
            public void onSucceed(ChannelData result) {
                mChannelListAdapter.setChannelData(result);
                mChannelListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {

            }
        }, mCategory.getId());
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category mCategory) {
        this.mCategory = mCategory;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EventBus.getDefault().post(mChannelListAdapter.getItem(position));
    }
}

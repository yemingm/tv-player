package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upyun.tvplayer.model.Channel;
import com.upyun.tvplayer.model.ChannelData;

public class ChannelListAdapter extends BaseAdapter {

    private ChannelData channelData;
    private Context mContext;

    public ChannelListAdapter(ChannelData channelData, Context context) {
        this.channelData = channelData;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (channelData != null) {
            return channelData.getCount();
        }
        return 0;
    }

    @Override
    public Channel getItem(int position) {
        return channelData.getChannels().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Channel channel = getItem(position);
        TextView textView = new TextView(mContext);
        textView.setText(channel.getChannelName());
        textView.setTextSize(30);
        return textView;
    }

    public void setChannelData(ChannelData channelData) {
        this.channelData = channelData;
    }
}

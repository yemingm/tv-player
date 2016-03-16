package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upyun.tvplayer.model.ChannelList;

public class ChannelFragAdapter extends BaseAdapter {

    private ChannelList channelList;
    private Context mContext;

    public ChannelFragAdapter(ChannelList channelList, Context context) {
        this.channelList = channelList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (channelList != null) {
            return channelList.getCount();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return channelList.getChannels();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(mContext);
        textView.setText(channelList.getChannels().get(position).getChannelName());
        textView.setTextSize(30);
        return textView;
    }

    public ChannelList getChannelList() {
        return channelList;
    }

    public void setChannelList(ChannelList channelList) {
        this.channelList = channelList;
    }
}

package com.upyun.tvplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.upyun.tvplayer.R;
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
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_channel,null,false);
            holder.tvChannelID = (TextView) convertView.findViewById(R.id.tv_channel_id);
            holder.tvChannelName = (TextView) convertView.findViewById(R.id.tv_channel_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Channel channel = getItem(position);
        holder.tvChannelID.setText(channel.getId()+"");
        holder.tvChannelName.setText(channel.getChannelName());
        return convertView;
    }

    class ViewHolder {
        TextView tvChannelID;
        TextView tvChannelName;
    }

    public void setChannelData(ChannelData channelData) {
        this.channelData = channelData;
    }
}

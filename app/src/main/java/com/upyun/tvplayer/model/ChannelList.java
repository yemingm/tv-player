package com.upyun.tvplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChannelList implements Serializable {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("channels")
    @Expose
    private Channels channels;
    @SerializedName("total")
    @Expose
    private Integer total;

    /**
     * @return The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return The channels
     */
    public Channels getChannels() {
        return channels;
    }

    /**
     * @param channels The channels
     */
    public void setChannels(Channels channels) {
        this.channels = channels;
    }

    /**
     * @return The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ChannelList{" +
                "channels=" + channels +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}

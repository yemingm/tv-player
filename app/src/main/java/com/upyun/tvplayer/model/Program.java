package com.upyun.tvplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Program implements Serializable {
    @SerializedName("zero_timestamp")
    @Expose
    private Integer zeroTimestamp;
    @SerializedName("channel_id")
    @Expose
    private Integer channelId;
    @SerializedName("program_list")
    @Expose
    private List<ProgramList> programList = new ArrayList<ProgramList>();

    /**
     * @return The zeroTimestamp
     */
    public Integer getZeroTimestamp() {
        return zeroTimestamp;
    }

    /**
     * @param zeroTimestamp The zero_timestamp
     */
    public void setZeroTimestamp(Integer zeroTimestamp) {
        this.zeroTimestamp = zeroTimestamp;
    }

    /**
     * @return The channelId
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * @param channelId The channel_id
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * @return The programList
     */
    public List<ProgramList> getProgramList() {
        return programList;
    }

    /**
     * @param programList The program_list
     */
    public void setProgramList(List<ProgramList> programList) {
        this.programList = programList;
    }

    @Override
    public String toString() {
        return "Program{" +
                "channelId=" + channelId +
                ", zeroTimestamp=" + zeroTimestamp +
                ", programList=" + programList +
                '}';
    }
}

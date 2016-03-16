package com.upyun.tvplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Channels implements Serializable {

    @SerializedName("delay")
    @Expose
    private Integer delay;
    @SerializedName("channel_name")
    @Expose
    private String channelName;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("input_and_output")
    @Expose
    private List<InputAndOutput> inputAndOutput = new ArrayList<InputAndOutput>();
    @SerializedName("switch_video_status")
    @Expose
    private String switchVideoStatus;
    @SerializedName("server_group")
    @Expose
    private Integer serverGroup;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("broadcast_category")
    @Expose
    private Integer broadcastCategory;

    /**
     *
     * @return
     * The delay
     */
    public Integer getDelay() {
        return delay;
    }

    /**
     *
     * @param delay
     * The delay
     */
    public void setDelay(Integer delay) {
        this.delay = delay;
    }

    /**
     *
     * @return
     * The channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     *
     * @param channelName
     * The channel_name
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The inputAndOutput
     */
    public List<InputAndOutput> getInputAndOutput() {
        return inputAndOutput;
    }

    /**
     *
     * @param inputAndOutput
     * The input_and_output
     */
    public void setInputAndOutput(List<InputAndOutput> inputAndOutput) {
        this.inputAndOutput = inputAndOutput;
    }

    /**
     *
     * @return
     * The switchVideoStatus
     */
    public String getSwitchVideoStatus() {
        return switchVideoStatus;
    }

    /**
     *
     * @param switchVideoStatus
     * The switch_video_status
     */
    public void setSwitchVideoStatus(String switchVideoStatus) {
        this.switchVideoStatus = switchVideoStatus;
    }

    /**
     *
     * @return
     * The serverGroup
     */
    public Integer getServerGroup() {
        return serverGroup;
    }

    /**
     *
     * @param serverGroup
     * The server_group
     */
    public void setServerGroup(Integer serverGroup) {
        this.serverGroup = serverGroup;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The broadcastCategory
     */
    public Integer getBroadcastCategory() {
        return broadcastCategory;
    }

    /**
     *
     * @param broadcastCategory
     * The broadcast_category
     */
    public void setBroadcastCategory(Integer broadcastCategory) {
        this.broadcastCategory = broadcastCategory;
    }

    @Override
    public String toString() {
        return "Channels{" +
                "broadcastCategory=" + broadcastCategory +
                ", delay=" + delay +
                ", channelName='" + channelName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", inputAndOutput=" + inputAndOutput +
                ", switchVideoStatus='" + switchVideoStatus + '\'' +
                ", serverGroup=" + serverGroup +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
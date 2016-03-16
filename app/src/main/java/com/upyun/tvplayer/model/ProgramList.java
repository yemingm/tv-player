package com.upyun.tvplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProgramList implements Serializable {

    @SerializedName("program_name")
    @Expose
    private String programName;
    @SerializedName("start_time")
    @Expose
    private Integer startTime;
    @SerializedName("end_time")
    @Expose
    private Integer endTime;

    /**
     * @return The programName
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * @param programName The program_name
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * @return The startTime
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * @param startTime The start_time
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * @return The endTime
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * @param endTime The end_time
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ProgramList{" +
                "endTime=" + endTime +
                ", programName='" + programName + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}


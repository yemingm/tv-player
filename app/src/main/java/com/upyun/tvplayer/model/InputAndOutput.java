package com.upyun.tvplayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InputAndOutput implements Serializable {

    @SerializedName("time_shift")
    @Expose
    private Boolean timeShift;
    @SerializedName("fragment_duration")
    @Expose
    private Integer fragmentDuration;
    @SerializedName("output_url")
    @Expose
    private String outputUrl;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("input_method")
    @Expose
    private String inputMethod;
    @SerializedName("pure_music_output")
    @Expose
    private Boolean pureMusicOutput;
    @SerializedName("review_duration")
    @Expose
    private Integer reviewDuration;
    @SerializedName("output_protocol")
    @Expose
    private String outputProtocol;
    @SerializedName("input_source")
    @Expose
    private String inputSource;
    @SerializedName("P2P")
    @Expose
    private Boolean P2P;

    /**
     * @return The timeShift
     */
    public Boolean getTimeShift() {
        return timeShift;
    }

    /**
     * @param timeShift The time_shift
     */
    public void setTimeShift(Boolean timeShift) {
        this.timeShift = timeShift;
    }

    /**
     * @return The fragmentDuration
     */
    public Integer getFragmentDuration() {
        return fragmentDuration;
    }

    /**
     * @param fragmentDuration The fragment_duration
     */
    public void setFragmentDuration(Integer fragmentDuration) {
        this.fragmentDuration = fragmentDuration;
    }

    /**
     * @return The outputUrl
     */
    public String getOutputUrl() {
        return outputUrl;
    }

    /**
     * @param outputUrl The output_url
     */
    public void setOutputUrl(String outputUrl) {
        this.outputUrl = outputUrl;
    }

    /**
     * @return The sourceUrl
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * @param sourceUrl The source_url
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    /**
     * @return The inputMethod
     */
    public String getInputMethod() {
        return inputMethod;
    }

    /**
     * @param inputMethod The input_method
     */
    public void setInputMethod(String inputMethod) {
        this.inputMethod = inputMethod;
    }

    /**
     * @return The pureMusicOutput
     */
    public Boolean getPureMusicOutput() {
        return pureMusicOutput;
    }

    /**
     * @param pureMusicOutput The pure_music_output
     */
    public void setPureMusicOutput(Boolean pureMusicOutput) {
        this.pureMusicOutput = pureMusicOutput;
    }

    /**
     * @return The reviewDuration
     */
    public Integer getReviewDuration() {
        return reviewDuration;
    }

    /**
     * @param reviewDuration The review_duration
     */
    public void setReviewDuration(Integer reviewDuration) {
        this.reviewDuration = reviewDuration;
    }

    /**
     * @return The outputProtocol
     */
    public String getOutputProtocol() {
        return outputProtocol;
    }

    /**
     * @param outputProtocol The output_protocol
     */
    public void setOutputProtocol(String outputProtocol) {
        this.outputProtocol = outputProtocol;
    }

    /**
     * @return The inputSource
     */
    public String getInputSource() {
        return inputSource;
    }

    /**
     * @param inputSource The input_source
     */
    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    /**
     * @return The P2P
     */
    public Boolean getP2P() {
        return P2P;
    }

    /**
     * @param P2P The P2P
     */
    public void setP2P(Boolean P2P) {
        this.P2P = P2P;
    }

    @Override
    public String toString() {
        return "InputAndOutput{" +
                "fragmentDuration=" + fragmentDuration +
                ", timeShift=" + timeShift +
                ", outputUrl='" + outputUrl + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", inputMethod='" + inputMethod + '\'' +
                ", pureMusicOutput=" + pureMusicOutput +
                ", reviewDuration=" + reviewDuration +
                ", outputProtocol='" + outputProtocol + '\'' +
                ", inputSource='" + inputSource + '\'' +
                ", P2P=" + P2P +
                '}';
    }
}
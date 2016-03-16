package com.upyun.tvplayer.Exception;

public class RespException extends UpYunException {

    private int code;

    public int code() {
        return code;
    }

    public RespException(String msg, int code) {
        super(msg);
        this.code = code;
    }
}

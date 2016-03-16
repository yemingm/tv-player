package com.upyun.tvplayer.net;

import com.google.gson.Gson;
import com.upyun.tvplayer.Exception.UpYunException;
import com.upyun.tvplayer.listener.UIListener;
import com.upyun.tvplayer.model.Program;

import okhttp3.Call;

public class ProgramAPI extends BaseAPI<Program> {

    @Override
    Program onResult(Call call, String data) throws UpYunException {
        Gson gson = new Gson();
        Program program = gson.fromJson(data, Program.class);
        return program;
    }

    public Call getProgram(UIListener<Program> uiListener, Week week, WeekDay weekDay, int channelId) {
        String url = HOST + program + "?api_key=" + KEY + "&channel_id=" + channelId + "&week=" + week + "&week_day=" + weekDay;
        System.out.println(url);
        return get(uiListener, url);
    }

    public enum WeekDay {
        Mon, Tue, Wed, Thu, Fri, Sat, Sun
    }

    public enum Week {
        this_week, next_week,
    }

}



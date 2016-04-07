package com.upyun.tvplayer.util;

import com.upyun.tvplayer.model.Channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class LocalSave {
    private static File tempFile = new File(context.getCacheDir(), "metrics");


    public static LocalSave getInstance() {
        return ;
    }
    public static void saveChannel(Channel channel) {
        ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(tempFile));
        objectOutput.writeObject(channel);
    }
}

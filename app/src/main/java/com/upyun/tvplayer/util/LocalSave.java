package com.upyun.tvplayer.util;

import android.content.Context;

import com.upyun.tvplayer.model.Channel;
import com.upyun.tvplayer.model.ProgramList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalSave {
    private static final String CHANNEL = "channel";
    private static final String PROGRAM = "program";

    public static boolean saveChannel(Context context, Channel channel) {
        File tempFile = new File(context.getCacheDir(), CHANNEL);
        tempFile.delete();
        try {
            tempFile.createNewFile();
            ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(tempFile));
            objectOutput.writeObject(channel);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Channel getChannel(Context context) {
        File tempFile = new File(context.getCacheDir(), CHANNEL);
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(tempFile));
            return (Channel) os.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean saveProgram(Context context, ProgramList channel) {
        File tempFile = new File(context.getCacheDir(), PROGRAM);
        tempFile.delete();
        try {
            tempFile.createNewFile();
            ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(tempFile));
            objectOutput.writeObject(channel);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static ProgramList getProgram(Context context) {
        File tempFile = new File(context.getCacheDir(), PROGRAM);
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(tempFile));
            return (ProgramList) os.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}

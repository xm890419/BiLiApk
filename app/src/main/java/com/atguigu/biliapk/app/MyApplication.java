package com.atguigu.biliapk.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by 熊猛 on 2017/3/20.
 */

public class MyApplication extends Application {
    private static Context context;
    private static Thread mainThread;
    private static int threadid;
    private static Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        threadid = android.os.Process.myPid();
        handler = new Handler();

    }

    public static Context getContext() {
        return context;
    }

    public static Thread getMainThread() {
        return mainThread;
    }

    public static int getThreadid() {
        return threadid;
    }

    public static Handler getHandler() {
        return handler;
    }
}

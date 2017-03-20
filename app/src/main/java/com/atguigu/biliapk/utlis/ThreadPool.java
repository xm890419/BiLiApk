package com.atguigu.biliapk.utlis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 熊猛 on 2017/3/20.
 */

public class ThreadPool {
    private ThreadPool(){};
    private static ThreadPool threadPool = new ThreadPool();
    public static ThreadPool getInstance(){
        return threadPool;
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThread(){
        return executorService;
    }
}

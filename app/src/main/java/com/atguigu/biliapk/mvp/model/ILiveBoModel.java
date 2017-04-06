package com.atguigu.biliapk.mvp.model;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public interface ILiveBoModel {
    /**
     * 直播界面的联网请求
     * @param url
     * @param listener
     */
    public void getDataFromNet(String url,OnRequestListener listener);
    public void getDataFromNet1(String url,OnRequestListener1 listener);
    public void getDataFromNet2(String url,OnRequestListener2 listener);

    public void getDataFromNet3(String url, OnRequestListener3 listener);
}

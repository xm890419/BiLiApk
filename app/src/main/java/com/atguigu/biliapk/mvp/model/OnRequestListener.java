package com.atguigu.biliapk.mvp.model;

import com.atguigu.biliapk.bean.LiveBean;

/**
 * Created by 熊猛 on 2017/4/6.
 * 请求的监听
 */

public interface OnRequestListener {
    /**
     * 当请求成功时回调
     * @param liveBean
     */
    void onSuccess(LiveBean liveBean);

    /**
     * 当请求失败时回调
     * @param ex
     */
    void onFailed(Exception ex);
}

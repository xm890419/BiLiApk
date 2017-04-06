package com.atguigu.biliapk.mvp.view;

import com.atguigu.biliapk.bean.AnimBean;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public interface ILiveBoView2 {
    public void hideLoading();
    public void showLoading();

    /**
     * 当请求成功时回调
     * @param animBean
     */
    void onSuccess(AnimBean animBean);

    /**
     * 当请求失败时回调
     * @param ex
     */
    void onFailed(Exception ex);

    String getUrl();
}

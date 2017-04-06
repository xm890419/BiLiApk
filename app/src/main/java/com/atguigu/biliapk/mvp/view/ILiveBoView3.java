package com.atguigu.biliapk.mvp.view;

import com.atguigu.biliapk.bean.TagBean;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public interface ILiveBoView3 {
    public void hideLoading();
    public void showLoading();

    /**
     * 当请求成功时回调
     * @param tagBean
     */
    void onSuccess(TagBean tagBean);

    /**
     * 当请求失败时回调
     * @param ex
     */
    void onFailed(Exception ex);

    String getUrl();
}

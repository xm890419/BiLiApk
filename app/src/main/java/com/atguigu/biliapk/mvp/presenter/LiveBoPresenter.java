package com.atguigu.biliapk.mvp.presenter;

import com.atguigu.biliapk.bean.LiveBean;
import com.atguigu.biliapk.mvp.model.ILiveBoModel;
import com.atguigu.biliapk.mvp.model.LiveBoModelImpl;
import com.atguigu.biliapk.mvp.model.OnRequestListener;
import com.atguigu.biliapk.mvp.view.ILiveBoView;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public class LiveBoPresenter {
    private ILiveBoModel iLiveBoModel;
    private ILiveBoView iLiveBoView;
    public LiveBoPresenter(ILiveBoView iLiveBoView){
        this.iLiveBoView = iLiveBoView;
        this.iLiveBoModel = new LiveBoModelImpl();
    }
    public void getDataFromNet(){
        //显示加载效果
        iLiveBoView.showLoading();
        //请求网络
        iLiveBoModel.getDataFromNet(iLiveBoView.getUrl(), new OnRequestListener() {
            @Override
            public void onSuccess(LiveBean liveBean) {
                //隐藏加载效果
                iLiveBoView.hideLoading();
                //成功时回传给view
                iLiveBoView.onSuccess(liveBean);
            }

            @Override
            public void onFailed(Exception ex) {
                //隐藏加载效果
                iLiveBoView.hideLoading();
                iLiveBoView.onFailed(ex);
            }
        });
    }
}

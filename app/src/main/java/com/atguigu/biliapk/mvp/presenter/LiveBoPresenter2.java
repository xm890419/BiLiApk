package com.atguigu.biliapk.mvp.presenter;

import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.mvp.model.ILiveBoModel;
import com.atguigu.biliapk.mvp.model.LiveBoModelImpl;
import com.atguigu.biliapk.mvp.model.OnRequestListener2;
import com.atguigu.biliapk.mvp.view.ILiveBoView2;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public class LiveBoPresenter2 {
    private ILiveBoModel iLiveBoModel;
    private ILiveBoView2 iLiveBoView;
    public LiveBoPresenter2(ILiveBoView2 iLiveBoView){
        this.iLiveBoView = iLiveBoView;
        this.iLiveBoModel = new LiveBoModelImpl();
    }
    public void getDataFromNet2(){
        //显示加载效果
        iLiveBoView.showLoading();
        //请求网络
        iLiveBoModel.getDataFromNet2(iLiveBoView.getUrl(), new OnRequestListener2() {
            @Override
            public void onSuccess(AnimBean animBean) {
                //隐藏加载效果
                iLiveBoView.hideLoading();
                //成功时回传给view
                iLiveBoView.onSuccess(animBean);
            }

            @Override
            public void onFailed(Exception ex) {
                //隐藏加载效果
                iLiveBoView.hideLoading();
                iLiveBoView.onFailed(ex);
            }
            /*@Override
            public void onSuccess(RecommendBean recommendBean) {
                //隐藏加载效果
                iLiveBoView1.hideLoading();
                //成功时回传给view
                iLiveBoView1.onSuccess(recommendBean);
            }

            @Override
            public void onFailed(Exception ex) {
                //隐藏加载效果
                iLiveBoView1.hideLoading();
                iLiveBoView1.onFailed(ex);
            }*/
            /*@Override
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
            }*/
        });
    }
}

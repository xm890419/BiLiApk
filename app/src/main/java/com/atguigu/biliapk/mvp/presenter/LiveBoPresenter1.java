package com.atguigu.biliapk.mvp.presenter;

import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.mvp.model.ILiveBoModel;
import com.atguigu.biliapk.mvp.model.LiveBoModelImpl;
import com.atguigu.biliapk.mvp.model.OnRequestListener1;
import com.atguigu.biliapk.mvp.view.ILiveBoView1;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public class LiveBoPresenter1 {
    private ILiveBoModel iLiveBoModel;
    private ILiveBoView1 iLiveBoView;
    public LiveBoPresenter1(ILiveBoView1 iLiveBoView){
        this.iLiveBoView = iLiveBoView;
        this.iLiveBoModel = new LiveBoModelImpl();
    }
    public void getDataFromNet1(){
        //显示加载效果
        iLiveBoView.showRefreshing();
        //请求网络
        iLiveBoModel.getDataFromNet1(iLiveBoView.getUrl(), new OnRequestListener1() {
            @Override
            public void onSuccess(RecommendBean recommendBean) {
                //隐藏加载效果
                iLiveBoView.hideRefreshing();
                //成功时回传给view
                iLiveBoView.onSuccess(recommendBean);
            }

            @Override
            public void onFailed(Exception ex) {
                //隐藏加载效果
                iLiveBoView.hideRefreshing();
                iLiveBoView.onFailed(ex);
            }
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

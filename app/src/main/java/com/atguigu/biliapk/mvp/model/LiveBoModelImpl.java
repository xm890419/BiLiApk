package com.atguigu.biliapk.mvp.model;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.Rx.RxUtils;
import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.bean.LiveBean;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.bean.TagBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 熊猛 on 2017/4/6.
 */

public class LiveBoModelImpl implements ILiveBoModel{
    @Override
    public void getDataFromNet(String url, final OnRequestListener listener) {
        /*OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "==" + e.getMessage());
                listener.onFailed(e);
            }

            @Override
            public void onResponse(String response, int id) {
                //Log.e("TAG", "=="+response);
                LiveBean liveBean = JSON.parseObject(response, LiveBean.class);
                //processData(response);
                listener.onSuccess(liveBean);
                //swipeRefreshLayout.setRefreshing(false);
            }
        });*/
        RxUtils rxUtils = new RxUtils();
        rxUtils.getNet(url).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onFailure"+e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onFailure"+s.toString());
                        LiveBean liveBean = JSON.parseObject(s, LiveBean.class);
                        listener.onSuccess(liveBean);
                        //swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void getDataFromNet1(String url, final OnRequestListener1 listener) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.getNet(url).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onFailure"+e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onFailure"+s.toString());
                        RecommendBean recommendBean = JSON.parseObject(s, RecommendBean.class);
                        listener.onSuccess(recommendBean);
                        //swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void getDataFromNet2(String url, final OnRequestListener2 listener) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.getNet(url).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onFailure"+e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onFailure"+s.toString());
                        AnimBean animBean = JSON.parseObject(s, AnimBean.class);
                        listener.onSuccess(animBean);
                        //swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    @Override
    public void getDataFromNet3(String url, final OnRequestListener3 listener) {
        RxUtils rxUtils = new RxUtils();
        rxUtils.getNet(url).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onFailure"+e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("TAG", "onFailure"+s.toString());
                        TagBean tagBean = JSON.parseObject(s, TagBean.class);
                        listener.onSuccess(tagBean);
                        //swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}

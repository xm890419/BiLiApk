package com.atguigu.biliapk.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.activity.MainActivity;
import com.atguigu.biliapk.adapter.ThemAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.BannerBean;
import com.atguigu.biliapk.bean.ThemBean;
import com.atguigu.biliapk.utlis.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class ThemFragment extends BaseFragment {

    @BindView(R.id.rv_them)
    RecyclerView rvThem;
    private ThemBean.ResultBean resultBean;
    private List<BannerBean.ResultBean> result;
    private ThemAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_them, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();



    }
    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.THEM_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                //Log.e("TAG", "" + response);
                processData(response);

                OkHttpUtils.get().url(Constants.BANNER_URL).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "" + e.getMessage());
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        //Log.e("TAG", "" + response);
                        MainActivity activity = (MainActivity) mContext;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                processData1(response);
                                adapter = new ThemAdapter(mContext,resultBean,result);
                                rvThem.setAdapter(adapter);
                                GridLayoutManager manager = new GridLayoutManager(mContext,1);
                                rvThem.setLayoutManager(manager);
                            }
                        });

                    }
                });
            }
        });
    }
    private void processData1(String response) {
        BannerBean bannerBean = JSON.parseObject(response, BannerBean.class);
        Log.e("TAG", "解析数据成功==" + bannerBean.getResult().get(0).getTitle());
        result = bannerBean.getResult();
    }
    private void processData(String response) {
        ThemBean themBean = JSON.parseObject(response, ThemBean.class);
        Log.e("TAG11", "解析数据成功==" + themBean.getResult().getSerializing().get(0).getTitle());
        resultBean = themBean.getResult();



    }
}

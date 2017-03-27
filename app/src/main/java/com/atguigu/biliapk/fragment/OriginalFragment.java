package com.atguigu.biliapk.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.OriginalAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.OriginalBean;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/26.
 */

public class OriginalFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private String order;
    private boolean isRefreshing = false;

    private List<OriginalBean.DataBean> datas = new ArrayList<>();

    //private List<OriginalBean.DataBean> data;
    private OriginalAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_original, null);
        ButterKnife.bind(this, view);
        return null;
    }

    public static OriginalFragment newInstance(String order) {
        OriginalFragment originalFragment = new OriginalFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantUtil.EXTRA_ORDER, order);
        originalFragment.setArguments(bundle);
        return originalFragment;
    }

    @Override
    public void initData() {
        super.initData();
        order = getArguments().getString(ConstantUtil.EXTRA_ORDER);
        getDataFromNet();
        initRefreshLayout();
        //getDataFromNet();
    }

    private void initRecyclerView() {

    }

    private void setRecycleNoScroll() {
        recycle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return isRefreshing;
            }
        });
    }

    private void initRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

                swipeRefreshLayout.setRefreshing(true);
                isRefreshing = true;
                getDataFromNet();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                isRefreshing = true;
                datas.clear();
                getDataFromNet();
            }
        });
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.ORIGINAL_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "=="+e.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "=="+response);
                processData(response);
                datas.addAll(datas.subList(0,20));
                finishTask();
            }
        });
    }

    private void finishTask() {
        isRefreshing = false;
        swipeRefreshLayout.setRefreshing(false);
        adapter.notifyDataSetChanged();
    }

    private void processData(String response) {
        OriginalBean originalBean = JSON.parseObject(response, OriginalBean.class);
        datas = originalBean.getData();
        recycle.setHasFixedSize(true);

        adapter = new OriginalAdapter(mContext, datas);
        recycle.setAdapter(adapter);
        //recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recycle.setLayoutManager(manager);
        //setRecycleNoScroll();
    }
}

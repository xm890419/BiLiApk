package com.atguigu.biliapk.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.LiveAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.LiveBean;
import com.atguigu.biliapk.utlis.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class LiveFragment extends BaseFragment {
    @BindView(R.id.rl_live)
    RecyclerView rlLive;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    /*@BindView(R.id.btn_more)
    Button btnMore;*/


    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_live, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNet();
            }
        });
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.LIVE_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "==" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                //Log.e("TAG", "=="+response);
                processData(response);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void processData(String response) {
        LiveBean liveBean = JSON.parseObject(response, LiveBean.class);
        Log.e("TAG", "解析数据成功==" + liveBean.getData().getPartitions().get(0).getLives().get(0).getCover().getSrc());
        //设置RecyclerView的适配器
        LiveAdapter adapter = new LiveAdapter(mContext, liveBean.getData());
        rlLive.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        rlLive.setLayoutManager(manager);

        //设置布局管理器
        //rlLive.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        /*manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return 1;
            }
        });*/

    }
}

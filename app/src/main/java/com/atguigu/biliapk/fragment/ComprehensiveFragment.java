package com.atguigu.biliapk.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.ComprehensiveAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.utlis.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ComprehensiveFragment extends BaseFragment {
    @BindView(R.id.gv_zong)
    RecyclerView gvZong;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private List<RecommendBean.DataBean> datas;
    private ComprehensiveAdapter adapter;

    //private TextView textView;
    @Override
    protected View initView() {
        /*textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
        View view = View.inflate(mContext, R.layout.fragment_comprehensive, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //textView.setText("综合");
        getDataFromNet();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNet();
            }
        });
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.RECOMMEND_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "" + response);
                processData(response);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void processData(String response) {
        final RecommendBean recommendBean = JSON.parseObject(response, RecommendBean.class);
        datas = recommendBean.getData();
        Log.e("TAG", "解析数据成功==" + recommendBean.getData().get(0).getName().toString());
        adapter = new ComprehensiveAdapter(mContext, recommendBean.getData());
        gvZong.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2);
        gvZong.setLayoutManager(manager);
    }
}

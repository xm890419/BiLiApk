package com.atguigu.biliapk.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.TopicAdapter;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.bean.TopicBean;
import com.atguigu.biliapk.utlis.Constants;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import okhttp3.Call;

import static com.alibaba.fastjson.JSON.parseObject;

public class ActivityCenterActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.swipe_refresh_layout)
    MaterialRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_no_media)
    TextView tvNoMedia;
    private boolean isRefreshing = false;
    private TopicAdapter adapter;
    private TopicBean topicBean;

    @Override
    public void initListener() {
        //监听下拉和上拉刷新
        swipeRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    @Override
    public void initData() {

        getDataFromNet();
    }

    //是否加载更多
    private boolean isloadMore = false;

    class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            //            Toast.makeText(mContext, "下拉刷新", Toast.LENGTH_SHORT).show();
            //isloadMore = false;
            getDataFromNet();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            //Toast.makeText(TopicCenterActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
            isloadMore = true;
            getDataFromNet();
            //swipeRefreshLayout.finishRefresh();
        }
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.TOPIC_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "==" + response);
                processData(response);
                if (!isloadMore) {
                    //完成刷新
                    swipeRefreshLayout.finishRefresh();
                } else {
                    //把上拉刷新隐藏
                    swipeRefreshLayout.finishRefreshLoadMore();
                }
            }
        });
    }
    private List<TopicBean.ListBean> list;
    private void processData(String response) {
        /*topicBean = parseObject(response, TopicBean.class);
        adapter = new TopicAdapter(this, topicBean);
        recycle.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recycle.setLayoutManager(manager);*/


        if (!isloadMore) {
            topicBean = parseObject(response, TopicBean.class);
            list = topicBean.getList();
            if (list != null && list.size() > 0) {
                //有数据
                tvNoMedia.setVisibility(View.GONE);
                adapter = new TopicAdapter(this, topicBean);
                //设置适配器
                recycle.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                recycle.setLayoutManager(manager);

            } else {
                tvNoMedia.setVisibility(View.VISIBLE);
            }
        } else {
            //加载更多
            List<TopicBean.ListBean> lists = topicBean.getList();
            lists.addAll(list);
            //刷新适配器
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void initTitle() {
        toolbar.setTitle("话题中心");
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_topic_center;
    }

}

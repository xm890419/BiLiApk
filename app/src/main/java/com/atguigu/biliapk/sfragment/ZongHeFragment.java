package com.atguigu.biliapk.sfragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.HeaderViewRecyclerAdapter;
import com.atguigu.biliapk.adapter.ZongHeAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.SearchBean;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.Constants;
import com.atguigu.biliapk.utlis.EndlessRecyclerOnScrollListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/28.
 */

public class ZongHeFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.iv_search_loading)
    ImageView ivSearchLoading;
    @BindView(R.id.empty_view)
    ImageView emptyView;
    private HeaderViewRecyclerAdapter headerViewRecyclerAdapter;
    private SearchBean searchBean;

    public static ZongHeFragment newInstance(String content) {
        ZongHeFragment fragment = new ZongHeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstantUtil.EXTRA_CONTENT, content);
        fragment.setArguments(bundle);

        return fragment;
    }

    private String content;
    // 标志位 标志已经初始化完成。
    protected boolean isPrepared;
    //标志位 fragment是否可见
    protected boolean isVisible;
    private List<SearchBean.DataBean.ItemsBean.ArchiveBean> archives = new ArrayList<>();
    private ZongHeAdapter mAdapter;

    private int pageNum = 1;

    private int pageSize = 10;
    private View loadMoreView;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zonghe, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        /*content = getArguments().getString(ConstantUtil.EXTRA_CONTENT);
        isPrepared = true;
        lazyLoad();*/
        getDataFromNet();
    }
    /*protected void lazyLoad() {

        if (!isPrepared || !isVisible) {
            return;
        }

        initRecyclerView();
        getDataFromNet();
        isPrepared = false;
    }
    protected void initRecyclerView() {

    }*/

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.SEARCH_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "=="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "=="+response);
                processData(response);
                if (searchBean.getData().getItems().getArchive().size() < pageSize) {
                    loadMoreView.setVisibility(View.GONE);
                    headerViewRecyclerAdapter.removeFootView();
                }
                archives.addAll(searchBean.getData().getItems().getArchive());
                loadMoreView.setVisibility(View.GONE);
                //archiveHeadBangumiAdapter.notifyDataSetChanged();
                if (pageNum * pageSize - pageSize - 1 > 0) {
                    headerViewRecyclerAdapter.notifyItemRangeChanged(pageNum * pageSize - pageSize - 1,
                            pageSize);
                } else {
                    headerViewRecyclerAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void processData(String response) {
        searchBean = JSON.parseObject(response, SearchBean.class);
        archives = searchBean.getData().getItems().getArchive();
        recycle.setHasFixedSize(true);

        mAdapter = new ZongHeAdapter(mContext, archives);
        headerViewRecyclerAdapter = new HeaderViewRecyclerAdapter(mAdapter);
        recycle.setAdapter(headerViewRecyclerAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recycle.setLayoutManager(manager);
        createHeadView();
        createLoadMoreView();
        recycle.addOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {

            @Override
            public void onLoadMore(int i) {

                pageNum++;
                getDataFromNet();
                loadMoreView.setVisibility(View.VISIBLE);
            }
        });
    }
    private void createHeadView() {

        View headView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_search_archive_head_view, recycle, false);
        RecyclerView mHeadBangumiRecycler = (RecyclerView) headView.findViewById(
                R.id.search_archive_bangumi_head_recycler);
        mHeadBangumiRecycler.setHasFixedSize(false);
        mHeadBangumiRecycler.setNestedScrollingEnabled(true);
        mHeadBangumiRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*archiveHeadBangumiAdapter = new ArchiveHeadBangumiAdapter(mHeadBangumiRecycler, seasons);
        mHeadBangumiRecycler.setAdapter(archiveHeadBangumiAdapter);*/

        headerViewRecyclerAdapter.addHeaderView(headView);
    }
    private void createLoadMoreView() {

        loadMoreView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_load_more, recycle, false);
        headerViewRecyclerAdapter.addFooterView(loadMoreView);
        loadMoreView.setVisibility(View.GONE);
    }
}

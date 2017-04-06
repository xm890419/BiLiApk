package com.atguigu.biliapk.fragment;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.LiveAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.LiveBean;
import com.atguigu.biliapk.mvp.presenter.LiveBoPresenter;
import com.atguigu.biliapk.mvp.view.ILiveBoView;
import com.atguigu.biliapk.utlis.Constants;
import com.atguigu.biliapk.view.CustomEmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class LiveFragment extends BaseFragment implements ILiveBoView{
    @BindView(R.id.rl_live)
    RecyclerView rlLive;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.empty_layout)
    CustomEmptyView emptyLayout;

    private LiveBoPresenter presenter;

    private ProgressDialog dialog;

    /*@BindView(R.id.btn_more)
    Button btnMore;*/
    public static LiveFragment newInstance() {

        return new LiveFragment();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_live, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        dialog = new ProgressDialog(mContext);
        presenter = new LiveBoPresenter(LiveFragment.this);
        //getDataFromNet();
        presenter.getDataFromNet();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataFromNet();
            }
        });
    }
    private void processData(LiveBean liveBean) {
        //LiveBean liveBean = JSON.parseObject(response, LiveBean.class);
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

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void onSuccess(LiveBean liveBean) {
        processData(liveBean);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed(Exception ex) {
        Toast.makeText(mContext, "联网请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUrl() {
        return Constants.LIVE_URL;
    }
}

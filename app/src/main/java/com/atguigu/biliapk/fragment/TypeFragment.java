package com.atguigu.biliapk.fragment;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.AnimAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.mvp.presenter.LiveBoPresenter2;
import com.atguigu.biliapk.mvp.view.ILiveBoView2;
import com.atguigu.biliapk.utlis.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class TypeFragment extends BaseFragment implements ILiveBoView2 {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private LiveBoPresenter2 presenter;

    private ProgressDialog dialog;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        dialog = new ProgressDialog(mContext);
        presenter = new LiveBoPresenter2(TypeFragment.this);
        //getDataFromNet();
        presenter.getDataFromNet2();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataFromNet2();
            }
        });
    }

    /*private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.ANIM_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", ""+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", ""+response);
                processData(response);
            }
        });
    }*/

    private void processData(AnimBean animBean) {
        //AnimBean animBean = JSON.parseObject(response, AnimBean.class);
        List<AnimBean.DataBean> data = animBean.getData();
        AnimAdapter animAdapter = new AnimAdapter(mContext, data);
        recycle.setAdapter(animAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 1);
        recycle.setLayoutManager(manager);
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
    public void onSuccess(AnimBean animBean) {
        processData(animBean);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed(Exception ex) {
        Toast.makeText(mContext, "联网请求失败", Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getUrl() {
        return Constants.ANIM_URL;
    }
}

package com.atguigu.biliapk.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.AnimAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.AnimBean;
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

public class TypeFragment extends BaseFragment {
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
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
    }

    private void processData(String response) {
        AnimBean animBean = JSON.parseObject(response, AnimBean.class);
        List<AnimBean.DataBean> data = animBean.getData();
        AnimAdapter animAdapter = new AnimAdapter(mContext,data);
        recycle.setAdapter(animAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext,1);
        recycle.setLayoutManager(manager);
    }


}

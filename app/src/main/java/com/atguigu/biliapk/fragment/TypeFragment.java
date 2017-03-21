package com.atguigu.biliapk.fragment;

import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class TypeFragment extends BaseFragment {
    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.fragment_type,null);
        return view;
    }
}

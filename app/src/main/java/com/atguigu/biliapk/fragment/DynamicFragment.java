package com.atguigu.biliapk.fragment;

import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class DynamicFragment extends BaseFragment {
    @Override
    protected View initView() {
        View view  =View.inflate(mContext, R.layout.fragment_dynamic,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //textView.setText("动态");
    }
}

package com.atguigu.biliapk.fragment;

import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ComprehensiveFragment extends BaseFragment {
    //private TextView textView;
    @Override
    protected View initView() {
        /*textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;*/
        View view = View.inflate(mContext, R.layout.fragment_comprehensive,null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //textView.setText("综合");
    }
}

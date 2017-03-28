package com.atguigu.biliapk.sfragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.biliapk.base.BaseFragment;

/**
 * Created by 熊猛 on 2017/3/28.
 */

public class UPFragment extends BaseFragment {
    private TextView textView;
    @Override
    protected View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        textView.setText("UP");
    }
}

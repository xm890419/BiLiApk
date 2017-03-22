package com.atguigu.biliapk.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.RecommendViewPagerAdapter;
import com.atguigu.biliapk.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class RecommendFragment extends BaseFragment {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.iv_m)
    ImageView ivM;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private ArrayList<BaseFragment> fragments;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        ButterKnife.bind(this, view);
        return view;


    }

    @Override
    public void initData() {
        super.initData();

        initFragment();
        RecommendViewPagerAdapter adapter = new RecommendViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);


    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ComprehensiveFragment());
        fragments.add(new DynamicFragment());
    }

    @OnClick(R.id.iv_m)
    public void onClick() {
        Toast.makeText(mContext, "喜欢的标签", Toast.LENGTH_SHORT).show();
    }
}

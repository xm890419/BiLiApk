package com.atguigu.biliapk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.biliapk.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class RecommendViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<BaseFragment> fragments;
    private String[] titles = new String[]{"综合", "动态"};

    public RecommendViewPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

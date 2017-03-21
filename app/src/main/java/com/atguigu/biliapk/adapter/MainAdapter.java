package com.atguigu.biliapk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.biliapk.base.BaseFragment;

import java.util.List;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;
    private String[] titles = new String[]{"直播", "推荐","追番", "分类","发现"};
    public MainAdapter(FragmentManager fm, List<BaseFragment> fragments) {
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

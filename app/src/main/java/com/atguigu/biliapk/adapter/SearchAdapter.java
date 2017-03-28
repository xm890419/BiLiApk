package com.atguigu.biliapk.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.atguigu.biliapk.base.BaseFragment;

import java.util.List;

/**
 * Created by 熊猛 on 2017/3/28.
 */

public class SearchAdapter extends FragmentPagerAdapter {


    private final List<BaseFragment> fragments;
    private final List<String> datas;

    public SearchAdapter(FragmentManager fm, List<String> titles, List<BaseFragment> fragments) {
        super(fm);
        this.datas = titles;
        this.fragments= fragments;
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
        return datas.get(position);
    }
}

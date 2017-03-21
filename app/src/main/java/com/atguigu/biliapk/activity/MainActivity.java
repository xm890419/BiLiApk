package com.atguigu.biliapk.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.MainAdapter;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.fragment.FoundFragment;
import com.atguigu.biliapk.fragment.LiveFragment;
import com.atguigu.biliapk.fragment.RecommendFragment;
import com.atguigu.biliapk.fragment.ThemFragment;
import com.atguigu.biliapk.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_toolbar)
    CoordinatorLayout activityToolbar;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ll_left)
    RelativeLayout llLeft;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.tv_vip)
    TextView tvVip;
    @BindView(R.id.tv_point)
    TextView tvPoint;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_later)
    TextView tvLater;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_lens)
    TextView tvLens;
    @BindView(R.id.tv_settings)
    TextView tvSettings;
    @BindView(R.id.dl_left)
    DrawerLayout dlLeft;
    @BindView(R.id.tv_left)
    TextView tvLeft;

    private List<BaseFragment> fragments;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        initFramgent();
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFramgent() {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new ThemFragment());
        fragments.add(new TypeFragment());
        fragments.add(new FoundFragment());
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.tv_left, R.id.tablayout, R.id.view_pager, R.id.activity_toolbar, R.id.iv, R.id.tv, R.id.ll_left, R.id.tv_home, R.id.tv_vip, R.id.tv_point, R.id.tv_download, R.id.tv_later, R.id.tv_star, R.id.tv_history, R.id.tv_people, R.id.tv_balance, R.id.tv_lens, R.id.tv_settings, R.id.dl_left})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                //打开DrawLayout
                dlLeft.openDrawer(Gravity.LEFT);
                break;
            case R.id.tablayout:
                break;
            case R.id.view_pager:
                break;
            case R.id.activity_toolbar:
                break;
            case R.id.iv:
                break;
            case R.id.tv:
                break;
            case R.id.ll_left:
                break;
            case R.id.tv_home:
                break;
            case R.id.tv_vip:
                break;
            case R.id.tv_point:
                break;
            case R.id.tv_download:
                break;
            case R.id.tv_later:
                break;
            case R.id.tv_star:
                break;
            case R.id.tv_history:
                break;
            case R.id.tv_people:
                break;
            case R.id.tv_balance:
                break;
            case R.id.tv_lens:
                break;
            case R.id.tv_settings:
                break;
            case R.id.dl_left:
                break;
        }
    }
}

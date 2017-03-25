package com.atguigu.biliapk.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.view.CircleProgressView;

import butterknife.BindView;

public class GameActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.circle_progress)
    CircleProgressView circleProgress;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        showProgressBar();
        hideProgressBar();
    }

    @Override
    public void initTitle() {
        toolbar.setTitle("游戏中心");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_game;
    }

    public void showProgressBar() {

        circleProgress.setVisibility(View.VISIBLE);
        circleProgress.spin();
        recycle.setVisibility(View.GONE);
    }



    public void hideProgressBar() {

        circleProgress.setVisibility(View.GONE);
        circleProgress.stopSpinning();
        recycle.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

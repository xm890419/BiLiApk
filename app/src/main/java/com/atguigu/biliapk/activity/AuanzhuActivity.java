package com.atguigu.biliapk.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;

import butterknife.BindView;

public class AuanzhuActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.iv_guanzhu)
    TextView ivGuanzhu;
    @BindView(R.id.lv_guanzhu)
    ListView lvGuanzhu;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.activity_auanzhu)
    LinearLayout activityAuanzhu;

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataFromNet();
            }
        });
    }

    private void getDataFromNet() {
        toolbar.setTitle("关注主播");
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    @Override
    public void initTitle() {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                finish();
                break;
        }*/
        finish();
        return super.onOptionsItemSelected(item);
    }

        @Override
    public int getLayoutid() {
            return R.layout.activity_auanzhu;
    }
   /* @Override
    public void onBackPressed() {
            finish();
    }*/

}

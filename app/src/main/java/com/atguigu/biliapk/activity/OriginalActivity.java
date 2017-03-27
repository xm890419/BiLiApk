package com.atguigu.biliapk.activity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.fragment.OriginalFragment;
import com.atguigu.biliapk.view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import butterknife.BindView;

public class OriginalActivity extends BaseActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;

    private String[] titles = new String[] { "原创", "全站", "番剧" };

    private String[] orders = new String[] { "origin-03.json", "all-03.json", "all-3-33.json" };

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        viewPager.setAdapter(
                new OriginalRankPagerAdapter(getSupportFragmentManager(), titles, orders));
        viewPager.setOffscreenPageLimit(orders.length);
        slidingTabs.setViewPager(viewPager);
        slidingTabs.setLayoutMode(TabLayout.MODE_SCROLLABLE);
    }
    private static class OriginalRankPagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles;

        private String[] orders;


        OriginalRankPagerAdapter(FragmentManager fm, String[] titles, String[] orders) {

            super(fm);
            this.titles = titles;
            this.orders = orders;
        }


        @Override
        public Fragment getItem(int position) {

            return OriginalFragment.newInstance(orders[position]);
        }


        @Override
        public CharSequence getPageTitle(int position) {

            return titles[position];
        }


        @Override
        public int getCount() {

            return titles.length;
        }
    }

    @Override
    public void initTitle() {
        toolbar.setTitle("排行榜");
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_original;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_rank, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.id_action_download:
                //离线缓存
                startActivity(new Intent(OriginalActivity.this, DownloadActivity.class));
                break;

            case R.id.id_action_search:
                //搜索
                //startActivity(new Intent(OriginalActivity.this, SearchActivity.class));
                //实例化
                SearchFragment searchFragment = SearchFragment.newInstance();
                //第二句 , 设置回调:
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
                        Toast.makeText(OriginalActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    }
                });
                searchFragment.show(getSupportFragmentManager(),SearchFragment.TAG);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.atguigu.biliapk.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.activity.DownloadActivity;
import com.atguigu.biliapk.activity.GameActivity;
import com.atguigu.biliapk.activity.MainActivity;
import com.atguigu.biliapk.activity.SearchActivity;
import com.atguigu.biliapk.adapter.MainAdapter;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.view.CircleImageView;
import com.atguigu.biliapk.view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/3/25.
 */

public class HomePageFragment extends BaseFragment {
    @BindView(R.id.toolbar_user_avatar)
    CircleImageView toolbarUserAvatar;
    @BindView(R.id.navigation_layout)
    LinearLayout navigationLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private List<BaseFragment> fragments;

    public static HomePageFragment newInstance() {
        return new HomePageFragment();
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        setHasOptionsMenu(true);
        initToolBar();
        initSearchView();
        initFramgent();
        MainAdapter adapter = new MainAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        slidingTabs.setViewPager(viewPager);
        slidingTabs.setLayoutMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(1);
    }

    private void initSearchView() {

        //初始化SearchBar
        searchView.setVoiceSearch(false);

        searchView.setCursorDrawable(R.drawable.custom_cursor);
        searchView.setEllipsize(true);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchActivity.launch(getActivity(), query);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }

    private void initToolBar() {

        toolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
    }

    private void initFramgent() {
        fragments = new ArrayList<>();
        fragments.add(new LiveFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new ThemFragment());
        fragments.add(new TypeFragment());
        fragments.add(new FoundFragment());
    }

    @OnClick(R.id.navigation_layout)
    void toggleDrawer() {

        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).toggleDrawer();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

        // 设置SearchViewItemMenu
        MenuItem item = menu.findItem(R.id.id_action_search);
        searchView.setMenuItem(item);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.id_action_game:
                //游戏中心
                //Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, GameActivity.class));
                break;

            case R.id.id_action_download:
                //离线缓存
                //Toast.makeText(mContext, "下载", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, DownloadActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isOpenSearchView() {

        return searchView.isSearchOpen();
    }


    public void closeSearchView() {

        searchView.closeSearch();
    }
}

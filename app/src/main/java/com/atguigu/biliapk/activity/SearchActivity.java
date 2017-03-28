package com.atguigu.biliapk.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.SearchAdapter;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.SearchBean;
import com.atguigu.biliapk.sfragment.FanJuFragment;
import com.atguigu.biliapk.sfragment.UPFragment;
import com.atguigu.biliapk.sfragment.YingShiFragment;
import com.atguigu.biliapk.sfragment.ZongHeFragment;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.Constants;
import com.atguigu.biliapk.utlis.StatusBarUtil;
import com.atguigu.biliapk.view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text_clear)
    ImageView searchTextClear;
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.search_card_view)
    CardView searchCardView;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    @BindView(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    @BindView(R.id.search_layout)
    LinearLayout searchLayout;
    @BindView(R.id.iv_search_loading)
    ImageView ivSearchLoading;
    private String content;

    private AnimationDrawable animationDrawable;
    private List<BaseFragment> fragments;
    private SearchBean searchBean;
    private SearchAdapter adapter;

    private List<String> titles = new ArrayList<>();
    private List<SearchBean.DataBean.NavBean> navs;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        initFramgent();
        Intent intent = getIntent();
        if (intent != null) {
            content = intent.getStringExtra(ConstantUtil.EXTRA_CONTENT);
        }

        ivSearchLoading.setImageResource(R.drawable.anim_search_loading);
        animationDrawable = (AnimationDrawable) ivSearchLoading.getDrawable();
        //showSearchAnim();
        searchEdit.clearFocus();
        searchEdit.setText(content);
        //setEmptyLayout();
        getDataFromNet();
    }

    private void initFramgent() {
        //hideSearchAnim();
        fragments = new ArrayList<>();
        ZongHeFragment zongHeFragment = ZongHeFragment.newInstance(content);

        fragments.add(zongHeFragment);
        fragments.add(new FanJuFragment());
        fragments.add(new UPFragment());
        fragments.add(new YingShiFragment());
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.SEARCH_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "=="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
               // Log.e("TAG", "==");
                processData(response);
            }
        });
    }

    private void processData(String response) {
        searchBean = JSON.parseObject(response, SearchBean.class);
        navs = searchBean.getData().getNav();
        titles.add("综合");
        titles.add(navs.get(0).getName() + "(" + checkNumResults(navs.get(0).getTotal()) + ")");
        titles.add(navs.get(1).getName() + "(" + checkNumResults(navs.get(1).getTotal()) + ")");
        titles.add(navs.get(2).getName() + "(" + checkNumResults(navs.get(2).getTotal()) + ")");
        adapter = new SearchAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(adapter);
        slidingTabs.setViewPager(viewPager);
        slidingTabs.setLayoutMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setCurrentItem(0);
        measureTabLayoutTextWidth(0);
        slidingTabs.setCurrentTab(0);
        adapter.notifyDataSetChanged();
        slidingTabs.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {

                measureTabLayoutTextWidth(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private String checkNumResults(int total) {
        return total > 100 ? "99+" : String.valueOf(total);
    }

    private void measureTabLayoutTextWidth(int position) {
        String title = titles.get(position);
        TextView titleView = slidingTabs.getTitleView(position);
        TextPaint paint = titleView.getPaint();
        float textWidth = paint.measureText(title);
        slidingTabs.setIndicatorWidth(textWidth / 3);
    }

    /*private void showSearchAnim() {

        ivSearchLoading.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.GONE);
        animationDrawable.start();
    }
    public void setEmptyLayout() {

        ivSearchLoading.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.GONE);
        //ivSearchLoading.setImageResource(R.drawable.search_failed);
    }
    private void hideSearchAnim() {

        ivSearchLoading.setVisibility(View.GONE);
        searchLayout.setVisibility(View.VISIBLE);
        animationDrawable.stop();
    }*/

    @Override
    public void initTitle() {
        //设置6.0以上StatusBar字体颜色
        StatusBarUtil.from(this)
                .setLightStatusBar(true)
                .process();
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.search_back)
    public void onClick() {
        finish();
    }

    public static void launch(Activity activity, String str) {
        Intent mIntent = new Intent(activity, SearchActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_CONTENT, str);
        activity.startActivity(mIntent);
    }
}

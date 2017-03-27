package com.atguigu.biliapk.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.StatusBarUtil;
import com.atguigu.biliapk.view.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            content = intent.getStringExtra(ConstantUtil.EXTRA_CONTENT);
        }

        ivSearchLoading.setImageResource(R.drawable.anim_search_loading);
        animationDrawable = (AnimationDrawable) ivSearchLoading.getDrawable();
        showSearchAnim();
        searchEdit.clearFocus();
        searchEdit.setText(content);
        setEmptyLayout();
    }
    private void showSearchAnim() {

        ivSearchLoading.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.GONE);
        animationDrawable.start();
    }
    public void setEmptyLayout() {

        ivSearchLoading.setVisibility(View.VISIBLE);
        searchLayout.setVisibility(View.GONE);
        ivSearchLoading.setImageResource(R.drawable.search_failed);
    }

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
        Intent mIntent = new Intent(activity, TopicCenterActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_CONTENT, str);
        activity.startActivity(mIntent);
    }
}

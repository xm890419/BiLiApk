package com.atguigu.biliapk.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.utlis.AppBarStateChangeEvent;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.DisplayUtil;
import com.atguigu.biliapk.utlis.SystemBarHelper;
import com.atguigu.biliapk.video.VideoCommentFragment;
import com.atguigu.biliapk.video.VideoIntroductionFragment;
import com.atguigu.biliapk.video.VideoPlayerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.video_preview)
    ImageView videoPreview;
    @BindView(R.id.tv_av)
    TextView tvAv;
    @BindView(R.id.tv_player)
    TextView tvPlayer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private AnimBean.DataBean.BodyBean bodyBean;
    private List<Fragment> fragments = new ArrayList<>();

    private List<String> titles = new ArrayList<>();
    private int av;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        toolbar.setTitle("");
        Intent intent = getIntent();
        if (intent != null) {
            av = intent.getIntExtra(ConstantUtil.EXTRA_AV, -1);
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设置还没收缩时状态下字体颜色
        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        //设置收缩后Toolbar上字体的颜色
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

        //设置StatusBar透明
        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setHeightAndPadding(this, toolbar);

        bodyBean = (AnimBean.DataBean.BodyBean) getIntent().getSerializableExtra("bodyBean");
        tvAv.setText(bodyBean.getGotoX()+bodyBean.getParam());
        Glide.with(this)
                .load(bodyBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(videoPreview);

        loadData();
        fab.setClickable(false);
        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.gray_20)));
        fab.setTranslationY(-getResources().getDimension(R.dimen.floating_action_button_size_half));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoPlayerActivity.launch(VideoActivity.this, bodyBean.getTitle());
            }
        });

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                setViewsTranslation(verticalOffset);
            }
        });
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeEvent() {

            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {

                if (state == State.EXPANDED) {
                    //展开状态
                    tvPlayer.setVisibility(View.GONE);
                    tvAv.setVisibility(View.VISIBLE);
                    toolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoActivity.this, 15), 0);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    tvPlayer.setVisibility(View.VISIBLE);
                    tvAv.setVisibility(View.GONE);
                    toolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoActivity.this, 150), 0);
                } else {
                    tvPlayer.setVisibility(View.GONE);
                    tvAv.setVisibility(View.VISIBLE);
                    toolbar.setContentInsetsRelative(DisplayUtil.dp2px(VideoActivity.this, 15), 0);
                }
            }
        });
    }

    private void loadData() {
        finishTask();
    }

    private void finishTask() {
        fab.setClickable(true);
        fab.setBackgroundTintList(
                ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        collapsingToolbar.setTitle("");


            Glide.with(VideoActivity.this)
                    .load(bodyBean.getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(videoPreview);
        VideoIntroductionFragment mVideoIntroductionFragment = VideoIntroductionFragment.newInstance(av);
        VideoCommentFragment mVideoCommentFragment = VideoCommentFragment.newInstance(av);

        fragments.add(mVideoIntroductionFragment);
        fragments.add(mVideoCommentFragment);

        setPagerTitle();
    }


    private void setPagerTitle() {

        titles.add("简介");
        titles.add("评论");

        VideoDetailsPagerAdapter mAdapter = new VideoDetailsPagerAdapter(getSupportFragmentManager(),
                fragments, titles);

        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setViewPager(viewPager);
        measureTabLayoutTextWidth(0);
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


    private void measureTabLayoutTextWidth(int position) {

        String title = titles.get(position);
        TextView titleView = tabLayout.getTitleView(position);
        TextPaint paint = titleView.getPaint();
        float textWidth = paint.measureText(title);
        tabLayout.setIndicatorWidth(textWidth / 3);
    }


    public static class VideoDetailsPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments;

        private List<String> titles;


        VideoDetailsPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {

            super(fm);
            this.fragments = fragments;
            this.titles = titles;
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

            return titles.get(position);
        }
    }

    private void setViewsTranslation(int target) {

        fab.setTranslationY(target);
        if (target == 0) {
            showFAB();
        } else if (target < 0) {
            hideFAB();
        }
    }
    private void showFAB() {

        fab.animate().scaleX(1f).scaleY(1f)
                .setInterpolator(new OvershootInterpolator())
                .start();

        fab.setClickable(true);
    }


    private void hideFAB() {

        fab.animate().scaleX(0f).scaleY(0f)
                .setInterpolator(new AccelerateInterpolator())
                .start();

        fab.setClickable(false);
    }
    public static void launch(Activity activity, String title) {

        Intent mIntent = new Intent(activity, VideoActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_TITLE, title);
        activity.startActivity(mIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_video, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}

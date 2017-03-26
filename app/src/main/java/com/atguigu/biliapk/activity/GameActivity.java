package com.atguigu.biliapk.activity;

import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.view.CircleProgressView;

import butterknife.BindView;

public class GameActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.circle_progress)
    CircleProgressView circleProgress;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        loadWeb("http://www.biligame.com/");
        showProgressBar();
        hideProgressBar();
    }
    private void loadWeb(String url) {
        WebSettings webSettings = webView.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击单击
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
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
        webView.setVisibility(View.GONE);
    }


    public void hideProgressBar() {

        circleProgress.setVisibility(View.GONE);
        circleProgress.stopSpinning();
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

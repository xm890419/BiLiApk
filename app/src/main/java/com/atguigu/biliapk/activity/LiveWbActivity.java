package com.atguigu.biliapk.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.utlis.ClipboardUtil;

import butterknife.BindView;

public class LiveWbActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.activity_live_wb)
    LinearLayout activityLiveWb;
    private String mTitle;
    private String link;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitle = getIntent().getStringExtra("title");
        link = getIntent().getStringExtra("link");
        loadWeb(link);
        toolbar.setTitle(TextUtils.isEmpty(mTitle) ? "详情" : mTitle);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initTitle() {


    }

    private void loadWeb(String url) {
        WebSettings webSettings = webview.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置双击单击
        webSettings.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
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
        webview.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_share:
                share();
                break;

            case R.id.menu_open:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
                break;

            case R.id.menu_copy:
                ClipboardUtil.setText(LiveWbActivity.this, link);
                Toast.makeText(LiveWbActivity.this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //分享
    private void share() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, "来自「哔哩哔哩」的分享:" + link);
        startActivity(Intent.createChooser(intent, mTitle));
    }

    @Override
    public void onBackPressed() {

        if (webview.canGoBack() && webview.copyBackForwardList().getSize() > 0
                && !webview.getUrl().equals(webview.copyBackForwardList()
                .getItemAtIndex(0).getOriginalUrl())) {
            webview.goBack();
        } else {
            finish();
        }
    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_live_wb;
    }

    @Override
    protected void onDestroy() {
        webview.destroy();
        super.onDestroy();
    }
}

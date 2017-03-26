package com.atguigu.biliapk.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.progressbar.NumberProgressBar;
import com.atguigu.biliapk.utlis.CommonUtil;
import com.atguigu.biliapk.view.CustomEmptyView;
import com.atguigu.biliapk.view.SpringScrollView;

import butterknife.BindView;

public class DownloadActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.download_scroll_view)
    SpringScrollView downloadScrollView;
    @BindView(R.id.empty_layout)
    CustomEmptyView emptyLayout;
    @BindView(R.id.progress_bar)
    NumberProgressBar progressBar;
    @BindView(R.id.cache_size_text)
    TextView cacheSizeText;
    @BindView(R.id.btn_start_all)
    TextView btnStartAll;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.btn_download_edit)
    TextView btnDownloadEdit;
    @BindView(R.id.download_refresh)
    ImageView downloadRefresh;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        long phoneTotalSize = CommonUtil.getPhoneTotalSize();
        long phoneAvailableSize = CommonUtil.getPhoneAvailableSize();
        //转换为G的显示单位
        String totalSizeStr = Formatter.formatFileSize(this, phoneTotalSize);
        String availabSizeStr = Formatter.formatFileSize(this, phoneAvailableSize);
        //计算占用空间的百分比
        int progress = countProgress(phoneTotalSize, phoneAvailableSize);
        progressBar.setProgress(progress);
        cacheSizeText.setText("主存储:" + totalSizeStr + "/" + "可用:" + availabSizeStr);

        CustomEmptyView mEmptyLayout = (CustomEmptyView) findViewById(R.id.empty_layout);
        assert mEmptyLayout != null;
        mEmptyLayout.setEmptyImage(R.drawable.img_tips_error_no_downloads);
        mEmptyLayout.setEmptyText("没有找到你的缓存哟");

    }

    @Override
    public void initTitle() {
        toolbar.setTitle("离线缓存");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.action_button_back_pressed_light);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_download;
    }

    private int countProgress(long phoneTotalSize, long phoneAvailableSize) {

        double totalSize = phoneTotalSize / (1024 * 3);
        double availabSize = phoneAvailableSize / (1024 * 3);
        //取整相减
        int size = (int) (Math.floor(totalSize) - Math.floor(availabSize));
        double v = (size / Math.floor(totalSize)) * 100;
        return (int) Math.floor(v);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_recommend, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_more) {
            Toast.makeText(DownloadActivity.this, "离线设置", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}

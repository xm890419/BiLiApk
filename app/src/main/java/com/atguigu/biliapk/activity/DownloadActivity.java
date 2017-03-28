package com.atguigu.biliapk.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.progressbar.NumberProgressBar;
import com.atguigu.biliapk.utlis.CommonUtil;
import com.atguigu.biliapk.utlis.ResponseListener;
import com.atguigu.biliapk.utlis.RetrofitUtils;
import com.atguigu.biliapk.view.MyProgressBar;
import com.atguigu.biliapk.view.SpringScrollView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownloadActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.download_scroll_view)
    SpringScrollView downloadScrollView;
    /*@BindView(R.id.empty_layout)
    CustomEmptyView emptyLayout;*/
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
    @BindView(R.id.tv_seekbar)
    TextView tvSeekbar;
    @BindView(R.id.seekbar)
    SeekBar seekbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private int maxDownloadNubmer; //最大同时下载数量
    private int downloadNumber; //当前下载数量

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

        /*CustomEmptyView mEmptyLayout = (CustomEmptyView) findViewById(R.id.empty_layout);
        assert mEmptyLayout != null;
        mEmptyLayout.setEmptyImage(R.drawable.img_tips_error_no_downloads);
        mEmptyLayout.setEmptyText("没有找到你的缓存哟");*/
        recyclerview.setAdapter(new Adapter());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekbar.setText("同时下载数量  " + (progress + 1) + "  ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(DownloadActivity.this).inflate(R.layout.item_download, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.setData();
        }

        @Override
        public int getItemCount() {
            return 10;
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progresss)
        MyProgressBar progresss;
        @BindView(R.id.tv_progress)
        TextView tvProgress;
        @BindView(R.id.bt_download)
        Button btDownload;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData() {
            btDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //seekbar 拖动范围0~4 +1相当于 同时下载数量范围为1~5
                    if (maxDownloadNubmer == 0) { //max=0 表示第一次下载赋值最大下载数量
                        maxDownloadNubmer = seekbar.getProgress() + 1;
                        Log.e("TAG", "ViewHolder onClick()" + maxDownloadNubmer);
                    }
                    if (downloadNumber >= maxDownloadNubmer) {
                        Toast.makeText(DownloadActivity.this, "同时下载数量过多 无法继续下载", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    downloadNumber++;
                    btDownload.setText("下载中!");
                    btDownload.setEnabled(false);
                    RetrofitUtils.getInstance().download(new File(getExternalFilesDir(null), getLayoutPosition() + ".apk"), new ResponseListener() {
                        @Override
                        public void onProgress(final long progress, final long total, boolean done) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    long l = progress * 100 / total;
                                    progresss.setProgress((int) l);

                                    String pro = (int) progress * 100 / (int) total + "%";
                                    String p = RetrofitUtils.formetFileSize(progress);
                                    String t = RetrofitUtils.formetFileSize(total);
                                    tvProgress.setText(p + " / " + t + "\t" + pro);
                                }
                            });
                        }

                        @Override
                        public void onResponse() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载完成!");
                                }
                            });
                        }

                        @Override
                        public void onFailure(final String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    downloadNumber--;
                                    btDownload.setText("下载出错!");
                                    btDownload.setEnabled(true);
                                    Log.e("TAG", "DownloadActivity onFailure()" + error);
                                }
                            });

                        }
                    });
                }
            });

        }
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

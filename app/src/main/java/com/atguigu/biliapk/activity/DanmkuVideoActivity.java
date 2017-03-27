package com.atguigu.biliapk.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.DanmakuVideoPlayer;
import com.atguigu.biliapk.listener.SampleListener;
import com.atguigu.biliapk.view.CircleImageView;
import com.atguigu.biliapk.view.ScrollWebView;
import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.atguigu.biliapk.R.id.webView;

/**
 * Created by guoshuyu on 2017/2/19.
 * 弹幕
 */


public class DanmkuVideoActivity extends AppCompatActivity {

    @BindView(R.id.post_detail_nested_scroll)
    NestedScrollView postDetailNestedScroll;

    @BindView(R.id.danmaku_player)
    DanmakuVideoPlayer danmakuVideoPlayer;

    @BindView(R.id.activity_detail_player)
    RelativeLayout activityDetailPlayer;
    @BindView(R.id.user_pic)
    CircleImageView userPic;
    @BindView(R.id.video_name)
    TextView videoName;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.live_num)
    TextView liveNum;
    @BindView(R.id.scroll_webView)
    ScrollWebView scrollWebView;

    private boolean isPlay;
    private boolean isPause;

    private OrientationUtils orientationUtils;
    private String url;
    private boolean isSamll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danmaku_layout);
        ButterKnife.bind(this);

        //使用自定义的全屏切换图片，!!!注意xml布局中也需要设置为一样的
        //必须在setUp之前设置
        danmakuVideoPlayer.setShrinkImageRes(R.drawable.custom_shrink);
        danmakuVideoPlayer.setEnlargeImageRes(R.drawable.custom_enlarge);
        /*String title = livesBean.getTitle();
        String playurl = livesBean.getPlayurl();
        String name = livesBean.getOwner().getName();
        String load = String.valueOf(Glide.with(mContext).load(livesBean.getOwner().getFace()));
        int online = livesBean.getOnline();
                    *//*LiveBean liveBean= new LiveBean();
                    LiveBean.DataBean.BannerBean bannerBean = liveBean.getData().getBanner().get(position);
                    bannerBean.setTitle(title);
                    bannerBean.setImg(link);*//*

        Intent intent = new Intent(mContext, DanmkuVideoActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("playurl",playurl);
        intent.putExtra("name",name);
        intent.putExtra("load",load);
        intent.putExtra("online",online);*/
        url = getIntent().getStringExtra("playurl");
        String name = getIntent().getStringExtra("name");
        String title = getIntent().getStringExtra("title");
        videoName.setText(title);
        String load = getIntent().getStringExtra("load");
        Glide.with(this).load(load).into(userPic);
        userName.setText(name);
        String online = getIntent().getStringExtra("online");
        liveNum.setText(online);
        /*String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";
        String url = "http://baobab.wdjcdn.com/14564977406580.mp4";*/
        //String url = "https://res.exexm.com/cw_145225549855002";
        danmakuVideoPlayer.setUp(url, true, null, "-" + name + "-" + "的直播间");

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        danmakuVideoPlayer.setThumbImageView(imageView);

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, danmakuVideoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        danmakuVideoPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        danmakuVideoPlayer.setRotateViewAuto(false);
        danmakuVideoPlayer.setLockLand(false);
        danmakuVideoPlayer.setShowFullAnimation(false);
        danmakuVideoPlayer.setNeedLockFull(true);

        //detailPlayer.setOpenPreView(true);
        danmakuVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                danmakuVideoPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
            }
        });

        danmakuVideoPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });

        danmakuVideoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
        WebSettings settings = scrollWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        scrollWebView.loadUrl("https://www.baidu.com");

        postDetailNestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (!danmakuVideoPlayer.isIfCurrentIsFullscreen() && scrollY >= 0 && isPlay) {
                    if (scrollY > danmakuVideoPlayer.getHeight()) {
                        //如果是小窗口就不需要处理
                        if (!isSamll) {
                            isSamll = true;
                            int size = CommonUtil.dip2px(DanmkuVideoActivity.this, 150);
                            danmakuVideoPlayer.showSmallVideo(new Point(size, size), true, true);
                            orientationUtils.setEnable(false);
                        }
                    } else {
                        if (isSamll) {
                            isSamll = false;
                            orientationUtils.setEnable(true);
                            //必须
                            danmakuVideoPlayer.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    danmakuVideoPlayer.hideSmallVideo();
                                }
                            }, 50);
                        }
                    }
                    danmakuVideoPlayer.setTranslationY((scrollY <= danmakuVideoPlayer.getHeight()) ? -scrollY : -danmakuVideoPlayer.getHeight());
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!danmakuVideoPlayer.isIfCurrentIsFullscreen()) {
                    danmakuVideoPlayer.startWindowFullscreen(DanmkuVideoActivity.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (danmakuVideoPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        danmakuVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        danmakuVideoPlayer.getTitleTextView().setText("测试视频");
        danmakuVideoPlayer.getBackButton().setVisibility(View.GONE);
    }

}
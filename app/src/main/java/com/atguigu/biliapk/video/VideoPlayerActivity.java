package com.atguigu.biliapk.video;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.Constants;
import com.atguigu.biliapk.video.callback.DanmukuSwitchListener;
import com.atguigu.biliapk.video.callback.VideoBackListener;
import com.atguigu.biliapk.view.CircleImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.ui.widget.DanmakuView;
import okhttp3.Call;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class VideoPlayerActivity extends AppCompatActivity implements DanmukuSwitchListener, VideoBackListener {

    @BindView(R.id.playerView)
    VideoPlayerView playerView;
    @BindView(R.id.sv_danmaku)
    DanmakuView svDanmaku;
    @BindView(R.id.video_loading_progress)
    CircleImageView videoLoadingProgress;
    @BindView(R.id.video_loading_text)
    TextView videoLoadingText;
    @BindView(R.id.buffering_indicator)
    RelativeLayout bufferingIndicator;
    @BindView(R.id.bili_anim)
    ImageView biliAnim;
    @BindView(R.id.video_start_info)
    TextView videoStartInfo;
    @BindView(R.id.video_start)
    RelativeLayout videoStart;
    @BindView(R.id.playerFrame)
    FrameLayout playerFrame;
    private String startText = "初始化播放器...";
    private AnimationDrawable mLoadingAnim;

    private DanmakuContext danmakuContext;
    private int cid;

    private String title;
    private int LastPosition = 0;
    private List<AnimBean.DataBean.BodyBean> body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);
        initData();
    }

    public void initToolBar() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setBackgroundDrawable(null);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            cid = intent.getIntExtra(ConstantUtil.EXTRA_CID, 0);
            title = intent.getStringExtra(ConstantUtil.EXTRA_TITLE);
        }
        initToolBar();
        initAnimation();
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        //配置播放器
        MediaController mMediaController = new MediaController(this);
        mMediaController.setTitle(title);
        playerView.setMediaController(mMediaController);
        playerView.setMediaBufferingIndicator(bufferingIndicator);
        playerView.requestFocus();
        playerView.setOnInfoListener(onInfoListener);
        playerView.setOnSeekCompleteListener(onSeekCompleteListener);
        playerView.setOnCompletionListener(onCompletionListener);
        playerView.setOnControllerEventsListener(onControllerEventsListener);
        //设置弹幕开关监听
        mMediaController.setDanmakuSwitchListener(this);
        //设置返回键监听
        mMediaController.setVideoBackEvent(this);

        //配置弹幕库
        svDanmaku.enableDanmakuDrawingCache(true);
        //设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        //滚动弹幕最大显示5行
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5);
        //设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        //设置弹幕样式
        danmakuContext = DanmakuContext.create();
        danmakuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3)
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.2f)
                .setScaleTextSize(0.8f)
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);

        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.ANIM_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    private void processData(String response) {
        AnimBean animBean = JSON.parseObject(response, AnimBean.class);
        body = animBean.getData().get(0).getBody();
        for (int i = 0; i < body.size(); i++) {
            Uri uri = Uri.parse(body.get(i).getUri());

            playerView.setVideoURI(uri);
            playerView.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(IMediaPlayer iMediaPlayer) {
                    mLoadingAnim.stop();
                    startText = startText + "【完成】\n视频缓冲中...";
                    videoStartInfo.setText(startText);
                    videoStart.setVisibility(View.GONE);
                }
            });
        }
    }

    /**
     * 初始化加载动画
     */
    private void initAnimation() {

        videoStart.setVisibility(View.VISIBLE);
        startText = startText + "【完成】\n解析视频地址...【完成】\n全舰弹幕填装...";
        videoStartInfo.setText(startText);
        mLoadingAnim = (AnimationDrawable) biliAnim.getBackground();
        mLoadingAnim.start();
    }

    /**
     * 视频缓冲事件回调
     */
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() {

        @Override
        public boolean onInfo(IMediaPlayer mp, int what, int extra) {

            if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START) {
                if (svDanmaku != null && svDanmaku.isPrepared()) {
                    svDanmaku.pause();
                    if (bufferingIndicator != null) {
                        bufferingIndicator.setVisibility(View.VISIBLE);
                    }
                }
            } else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
                if (svDanmaku != null && svDanmaku.isPaused()) {
                    svDanmaku.resume();
                }
                if (bufferingIndicator != null) {
                    bufferingIndicator.setVisibility(View.GONE);
                }
            }
            return true;
        }
    };

    /**
     * 视频跳转事件回调
     */
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener
            = new IMediaPlayer.OnSeekCompleteListener() {

        @Override
        public void onSeekComplete(IMediaPlayer mp) {

            if (svDanmaku != null && svDanmaku.isPrepared()) {
                svDanmaku.seekTo(mp.getCurrentPosition());
            }
        }
    };

    /**
     * 视频播放完成事件回调
     */
    private IMediaPlayer.OnCompletionListener onCompletionListener
            = new IMediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(IMediaPlayer mp) {

            if (svDanmaku != null && svDanmaku.isPrepared()) {
                svDanmaku.seekTo((long) 0);
                svDanmaku.pause();
            }
            playerView.pause();
        }
    };

    /**
     * 控制条控制状态事件回调
     */
    private VideoPlayerView.OnControllerEventsListener onControllerEventsListener
            = new VideoPlayerView.OnControllerEventsListener() {

        @Override
        public void onVideoPause() {

            if (svDanmaku != null && svDanmaku.isPrepared()) {
                svDanmaku.pause();
            }
        }


        @Override
        public void OnVideoResume() {

            if (svDanmaku != null && svDanmaku.isPaused()) {
                svDanmaku.resume();
            }
        }
    };


    @Override
    protected void onResume() {

        super.onResume();
        if (svDanmaku != null && svDanmaku.isPrepared() && svDanmaku.isPaused()) {
            svDanmaku.seekTo((long) LastPosition);
        }
        if (playerView != null && !playerView.isPlaying()) {
            playerView.seekTo(LastPosition);
        }
    }


    @Override
    protected void onPause() {

        super.onPause();
        if (playerView != null) {
            LastPosition = playerView.getCurrentPosition();
            playerView.pause();
        }

        if (svDanmaku != null && svDanmaku.isPrepared()) {
            svDanmaku.pause();
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        if (svDanmaku != null) {
            svDanmaku.release();
            svDanmaku = null;
        }
        if (mLoadingAnim != null) {
            mLoadingAnim.stop();
            mLoadingAnim = null;
        }
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        if (playerView != null && playerView.isDrawingCacheEnabled()) {
            playerView.destroyDrawingCache();
        }
        if (svDanmaku != null && svDanmaku.isPaused()) {
            svDanmaku.release();
            svDanmaku = null;
        }
        if (mLoadingAnim != null) {
            mLoadingAnim.stop();
            mLoadingAnim = null;
        }
    }


    /**
     * 弹幕开关回调
     */
    @Override
    public void setDanmakushow(boolean isShow) {

        if (svDanmaku != null) {
            if (isShow) {
                svDanmaku.show();
            } else {
                svDanmaku.hide();
            }
        }
    }


    /**
     * 退出界面回调
     */
    @Override
    public void back() {

        onBackPressed();
    }


    public static void launch(Activity activity, String title) {

        Intent mIntent = new Intent(activity, VideoPlayerActivity.class);
        //mIntent.putExtra(ConstantUtil.EXTRA_CID, cid);
        mIntent.putExtra(ConstantUtil.EXTRA_TITLE, title);
        activity.startActivity(mIntent);
    }
}

package com.atguigu.biliapk.video;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.AnimBean;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.Constants;
import com.atguigu.biliapk.utlis.NumberUtil;
import com.atguigu.biliapk.view.UserTagView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 * <p/>
 * 视频简介界面
 */
public class VideoIntroductionFragment extends BaseFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_play_time)
    TextView tvPlayTime;
    @BindView(R.id.tv_review_count)
    TextView tvReviewCount;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.share_num)
    TextView shareNum;
    @BindView(R.id.btn_share)
    LinearLayout btnShare;
    @BindView(R.id.coin_num)
    TextView coinNum;
    @BindView(R.id.btn_coin)
    LinearLayout btnCoin;
    @BindView(R.id.fav_num)
    TextView favNum;
    @BindView(R.id.btn_fav)
    LinearLayout btnFav;
    @BindView(R.id.btn_download)
    LinearLayout btnDownload;
    @BindView(R.id.author_tag)
    UserTagView authorTag;
    @BindView(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @BindView(R.id.layout_video_related)
    RelativeLayout layoutVideoRelated;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private int av;

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_video_introduction, null);
        ButterKnife.bind(this, view);
        return view;
    }

    public static VideoIntroductionFragment newInstance(int aid) {
        VideoIntroductionFragment fragment = new VideoIntroductionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantUtil.EXTRA_AV, aid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData() {
        super.initData();
        av = getArguments().getInt(ConstantUtil.EXTRA_AV);
        getDataFromNet(av);
    }

    private void getDataFromNet(int av) {
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

        for (int i = 0; i < animBean.getData().get(0).getBody().size(); i++) {
            AnimBean.DataBean.BodyBean bodyBean = animBean.getData().get(0).getBody().get(i);
            //设置视频标题
            tvTitle.setText(bodyBean.getTitle());
            //设置视频播放数量
            tvPlayTime.setText(NumberUtil.converString(bodyBean.getPlay()));
            //设置视频弹幕数量
            tvReviewCount.setText(NumberUtil.converString(bodyBean.getDanmaku()));
        }
    }
}

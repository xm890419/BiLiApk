package com.atguigu.biliapk.fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseFragment;
import com.atguigu.biliapk.bean.TagBean;
import com.atguigu.biliapk.utlis.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class FoundFragment extends BaseFragment {


    @BindView(R.id.search_edit)
    TextView searchEdit;
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.tags_layout)
    TagFlowLayout tagsLayout;
    @BindView(R.id.hide_tags_layout)
    TagFlowLayout hideTagsLayout;
    @BindView(R.id.hide_scroll_view)
    NestedScrollView hideScrollView;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.more_layout)
    LinearLayout moreLayout;
    @BindView(R.id.rl_group)
    RelativeLayout rlGroup;
    @BindView(R.id.rl_topic)
    RelativeLayout rlTopic;
    @BindView(R.id.rl_center)
    RelativeLayout rlCenter;
    @BindView(R.id.rl_black)
    RelativeLayout rlBlack;
    @BindView(R.id.rl_original)
    RelativeLayout rlOriginal;
    @BindView(R.id.rl_rank)
    RelativeLayout rlRank;
    @BindView(R.id.rl_game)
    RelativeLayout rlGame;
    @BindView(R.id.rl_shop)
    RelativeLayout rlShop;

    private boolean isShowMore = true;
    private TagBean tagBean;

    //private List<TagBean.DataBean.ListBean> dataBeen = new ArrayList<>();
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_found, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void initData() {
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.TAG_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "==" + response);
                processData(response);
            }
        });
    }

    private void processData(String response) {
        tagBean = JSON.parseObject(response, TagBean.class);
        Log.e("TAG", "" + tagBean.getData().getList().size());
        List<TagBean.DataBean.ListBean> list = tagBean.getData().getList();
        //获取热搜标签集合前9个默认显示
        List<TagBean.DataBean.ListBean> frontTags = list.subList(0, 9);
        tagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(frontTags) {
            @Override
            public View getView(FlowLayout parent, final int position, TagBean.DataBean.ListBean listBean) {
                TextView mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(listBean.getKeyword());
                //获取shapeDrawable
                GradientDrawable drawable = (GradientDrawable) mTags.getBackground();
                Random random = new Random();
                int red = random.nextInt(150) + 100;
                int green = random.nextInt(150) + 100;
                int blue = random.nextInt(150) + 100;
                //设置shape的背景色
                drawable.setColor(Color.rgb(red, green, blue));
                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "==" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return mTags;
            }
        });
        hideTagsLayout.setAdapter(new TagAdapter<TagBean.DataBean.ListBean>(list) {

            @Override
            public View getView(FlowLayout parent, final int position, TagBean.DataBean.ListBean listBean) {
                TextView mTags = (TextView) LayoutInflater.from(getActivity())
                        .inflate(R.layout.layout_tags_item, parent, false);
                mTags.setText(listBean.getKeyword());
                //获取shapeDrawable
                GradientDrawable drawable = (GradientDrawable) mTags.getBackground();
                Random random = new Random();
                int red = random.nextInt(150) + 100;
                int green = random.nextInt(150) + 100;
                int blue = random.nextInt(150) + 100;
                //设置shape的背景色
                drawable.setColor(Color.rgb(red, green, blue));
                mTags.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "==" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                return mTags;
            }
        });
    }


    @OnClick({R.id.search_edit, R.id.search_img, R.id.rl_group, R.id.rl_topic, R.id.rl_center, R.id.rl_black, R.id.rl_original, R.id.rl_rank, R.id.rl_game, R.id.rl_shop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_edit:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search_img:
                Toast.makeText(mContext, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_group:
                Toast.makeText(mContext, "兴趣圈", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_topic:
                Toast.makeText(mContext, "话题中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_center:
                Toast.makeText(mContext, "活动中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_black:
                Toast.makeText(mContext, "小黑屋", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_original:
                Toast.makeText(mContext, "原创排行榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_rank:
                Toast.makeText(mContext, "全区排行榜", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_game:
                Toast.makeText(mContext, "游戏中心", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_shop:
                Toast.makeText(mContext, "周边商城", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.more_layout)
    public void onClick() {
        if (isShowMore) {
            isShowMore = false;
            hideScrollView.setVisibility(View.VISIBLE);
            tvMore.setText("收起");
            tagsLayout.setVisibility(View.GONE);
            Drawable upDrawable = getResources().getDrawable(R.drawable.ic_arrow_up_gray_round);
            upDrawable.setBounds(0, 0, upDrawable.getMinimumWidth(), upDrawable.getMinimumHeight());
            tvMore.setCompoundDrawables(upDrawable, null, null, null);
        } else {
            isShowMore = true;
            hideScrollView.setVisibility(View.GONE);
            tvMore.setText("查看更多");
            tagsLayout.setVisibility(View.VISIBLE);
            Drawable downDrawable = getResources().getDrawable(R.drawable.ic_arrow_down_gray_round);
            downDrawable.setBounds(0, 0, downDrawable.getMinimumWidth(), downDrawable.getMinimumHeight());
            tvMore.setCompoundDrawables(downDrawable, null, null, null);
        }
    }
}

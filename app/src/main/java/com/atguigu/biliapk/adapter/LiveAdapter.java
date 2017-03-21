package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.LiveBean;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class LiveAdapter extends RecyclerView.Adapter {
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;
    private final Context mContext;
    private final LiveBean.DataBean result;
    private final LayoutInflater inflater;
    /**
     * 当前类型
     */
    public int currentType = BANNER;


    public LiveAdapter(Context mContext, LiveBean.DataBean data) {
        this.mContext = mContext;
        this.result = data;
        inflater = LayoutInflater.from(mContext);

    }

    //根据位置得到对应的类型

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //所有的类型写完后改成6
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_gridview, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(result.getBanner());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData();
        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner1;

        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(List<LiveBean.DataBean.BannerBean> banner) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner.size(); i++) {
                images.add(banner.get(i).getImg());
                images.add(banner.get(i).getImg());
            }
            banner1.setImages(images).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).crossFade().into(imageView);
                }
            }).start();
            //设置样式
            banner1.setBannerAnimation(BackgroundToForegroundTransformer.class);

            banner1.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_anchor)
        TextView tvAnchor;
        @BindView(R.id.tv_center)
        TextView tvCenter;
        @BindView(R.id.tv_clip)
        TextView tvClip;
        @BindView(R.id.tv_search)
        TextView tvSearch;
        @BindView(R.id.tv_category)
        TextView tvCategory;

        public ChannelViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setData() {
            tvAnchor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "关注", Toast.LENGTH_SHORT).show();
                }
            });
            tvCenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "中心", Toast.LENGTH_SHORT).show();
                }
            });
            tvClip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "小视频", Toast.LENGTH_SHORT).show();
                }
            });
            tvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                }
            });
            tvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "分类", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

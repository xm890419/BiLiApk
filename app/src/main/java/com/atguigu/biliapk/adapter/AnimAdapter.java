package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.AnimBean;
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
 * Created by 熊猛 on 2017/3/23.
 */

public class AnimAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private final List<AnimBean.DataBean> datas;
    /**
     * 头
     */
    public static final int TYPE = 0;
    /**
     * 动画区
     */
    public static final int ANIM = 2;
    /**
     * banner
     */
    public static final int BANNER = 1;
    /**
     * 话题
     */
    public static final int TOPIC = 3;
    private final LayoutInflater inflater;
    /**
     * 当前类型
     */
    public int currentType = TYPE;

    private TypeViewHolder typeViewHolder;


    @Override
    public int getItemViewType(int position) {
        if (position == TYPE) {
            currentType = TYPE;
        } else if (position == BANNER) {
            currentType = BANNER;
        } else if (position == ANIM) {
            currentType = ANIM;
        } else if (position == TOPIC) {
            currentType = TOPIC;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public AnimAdapter(Context mContext, List<AnimBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE) {
            return new TypeViewHolder(mContext, inflater.inflate(R.layout.adapter_type, parent, false));
        } else if (viewType == BANNER) {
            return new BanViewHolder(mContext, inflater.inflate(R.layout.adapter_ban, null));
        } else if (viewType == ANIM) {
            return new AnimViewHolder(mContext, inflater.inflate(R.layout.adapter_anim, null));
        } else if (viewType == TOPIC) {
            return new TopicViewHolder(mContext, inflater.inflate(R.layout.adapter_topic, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE) {
            typeViewHolder = (TypeViewHolder) holder;
            typeViewHolder.setData();
        } else if (getItemViewType(position) == BANNER) {
            BanViewHolder banViewHolder = (BanViewHolder) holder;
            List<AnimBean.DataBean.BannerBean.BottomBean> bottom = datas.get(0).getBanner().getBottom();
            banViewHolder.setData(bottom);
        } else if (getItemViewType(position) == ANIM) {
            AnimViewHolder animViewHolder = (AnimViewHolder) holder;
            animViewHolder.setData(datas.get(position));
        }else if(getItemViewType(position) == TOPIC) {
            TopicViewHolder topicViewHolder = (TopicViewHolder) holder;
            topicViewHolder.setData(datas.get(4));
        }
    }


    class TypeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.gv_guoman)
        GridView gvGuoman;

        public TypeViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData() {
            AnimGridAdapter gridAdapter = new AnimGridAdapter(mContext);
            typeViewHolder.gvGuoman.setAdapter(gridAdapter);
            gvGuoman.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            //直播
                            Toast.makeText(mContext, "直播", Toast.LENGTH_SHORT).show();
                            break;

                        case 1:
                            //番剧
                            Toast.makeText(mContext, "番剧", Toast.LENGTH_SHORT).show();
                            break;

                        case 2:
                            //动画
                            Toast.makeText(mContext, "动画", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            //国创
                            Toast.makeText(mContext, "国创", Toast.LENGTH_SHORT).show();
                            break;
                        case 4:
                            //音乐
                            Toast.makeText(mContext, "音乐", Toast.LENGTH_SHORT).show();
                            break;

                        case 5:
                            //舞蹈
                            Toast.makeText(mContext, "舞蹈", Toast.LENGTH_SHORT).show();
                            break;

                        case 6:
                            //游戏
                            Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();
                            break;

                        case 7:
                            //科技
                            Toast.makeText(mContext, "科技", Toast.LENGTH_SHORT).show();
                            break;

                        case 8:
                            //生活
                            Toast.makeText(mContext, "生活", Toast.LENGTH_SHORT).show();
                            break;

                        case 9:
                            //鬼畜
                            Toast.makeText(mContext, "鬼畜", Toast.LENGTH_SHORT).show();
                            break;

                        case 10:
                            //时尚
                            Toast.makeText(mContext, "时尚", Toast.LENGTH_SHORT).show();
                            break;

                        case 11:
                            //广告
                            Toast.makeText(mContext, "广告", Toast.LENGTH_SHORT).show();
                            break;

                        case 12:
                            //娱乐
                            Toast.makeText(mContext, "娱乐", Toast.LENGTH_SHORT).show();
                            break;

                        case 13:
                            //电影
                            Toast.makeText(mContext, "电影", Toast.LENGTH_SHORT).show();
                            break;

                        case 14:
                            //电视剧
                            Toast.makeText(mContext, "电视剧", Toast.LENGTH_SHORT).show();
                            break;

                        case 15:
                            // 游戏中心
                            Toast.makeText(mContext, "游戏中心", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }

    class AnimViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_kan)
        Button btnKan;
        @BindView(R.id.gv_paint)
        GridView gvPaint;
        @BindView(R.id.btn_m)
        Button btnM;
        @BindView(R.id.tv_refresh)
        TextView tvRefresh;

        public AnimViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(AnimBean.DataBean dataBean) {
            AnimationAdapter animationAdapter = new AnimationAdapter(mContext, dataBean);
            gvPaint.setAdapter(animationAdapter);
            gvPaint.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
            tvRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击刷新", Toast.LENGTH_SHORT).show();
                }
            });
            btnKan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "进去看看", Toast.LENGTH_SHORT).show();
                }
            });
            btnM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "点击进入专区", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class BanViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner_anim)
        Banner bannerAnim;

        public BanViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(List<AnimBean.DataBean.BannerBean.BottomBean> bottomBean) {
            List<String> images = new ArrayList<>();
            for (int i = 0; i < bottomBean.size(); i++) {
                images.add(bottomBean.get(i).getImage());
            }
            bannerAnim.setImages(images).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(mContext).load(path).crossFade().into(imageView);
                }
            }).start();
            //设置样式
            bannerAnim.setBannerAnimation(BackgroundToForegroundTransformer.class);
            bannerAnim.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_topic)
        Button btnTopic;
        @BindView(R.id.iv_topic)
        ImageView ivTopic;
        public TopicViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setData(AnimBean.DataBean dataBean) {
            btnTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "进去看看", Toast.LENGTH_SHORT).show();
                }
            });
            Glide.with(mContext).load(dataBean.getBody().get(0).getCover()).crossFade().into(ivTopic);
            ivTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "进入话题", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

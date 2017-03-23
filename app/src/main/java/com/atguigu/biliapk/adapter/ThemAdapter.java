package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.BannerBean;
import com.atguigu.biliapk.bean.ThemBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ThemAdapter extends RecyclerView.Adapter {
    private final Context mContext;
    private final LayoutInflater inflater;
    /**
     * 头
     */
    public static final int HEAD = 0;
    /**
     * 番剧推荐
     */
    public static final int PLAY = 1;
    /**
     * banner
     */
    public static final int BANNER = 2;
    /**
     * 国漫推荐
     */
    public static final int GUOMAN = 3;
    /**
     * 当前类型
     */
    public int currentType = PLAY;


    private List<BannerBean.ResultBean> result;

    private ThemBean.ResultBean resultBean;


    public ThemAdapter(final Context mContext, ThemBean.ResultBean resultBean, List<BannerBean.ResultBean> result) {
        this.mContext = mContext;
        this.resultBean = resultBean;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }
//根据位置得到对应的类型

    @Override
    public int getItemViewType(int position) {
        if (position == HEAD) {
            currentType = HEAD;
        } else if (position == PLAY) {
            currentType = PLAY;
        } else if (position == BANNER) {
            currentType = BANNER;
        } else if (position == GUOMAN) {
            currentType = GUOMAN;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //所有的类型写完后改成4
        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEAD) {
            return new HeadViewHolder(mContext, inflater.inflate(R.layout.adapter_head, null));
        } else if (viewType == PLAY) {
            return new PlayViewHolder(mContext, inflater.inflate(R.layout.adapter_play, null));
        } else if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.adapter_banner, null));
        } else if (viewType == GUOMAN) {
            return new GuoManViewHolder(mContext, inflater.inflate(R.layout.adapter_guoman, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.setData();
        }
        if (getItemViewType(position) == PLAY) {
            PlayViewHolder playViewHolder = (PlayViewHolder) holder;
            playViewHolder.setData(resultBean.getSerializing());
        } else if (getItemViewType(position) == BANNER) {
            BannerViewHolder bViewHolder = (BannerViewHolder) holder;

            bViewHolder.setData(result.get(position));
            //Log.e("TAG", "==" + result.get(position).getTitle());
        } else if (getItemViewType(position) == GUOMAN) {
            GuoManViewHolder viewHolder = (GuoManViewHolder) holder;
            viewHolder.setData(resultBean.getPrevious().getList());
        }
    }

    class PlayViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_paint)
        TextView tvPaint;
        @BindView(R.id.gv_paint)
        GridView gvPaint;

        public PlayViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);

        }

        public void setData(List<ThemBean.ResultBean.SerializingBean> serializingBeen) {
            tvPaint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "更多番剧", Toast.LENGTH_SHORT).show();
                }
            });
            PlayAdapter playAdapter = new PlayAdapter(mContext, serializingBeen);
            gvPaint.setAdapter(playAdapter);
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_banner)
        ImageView ivBanner;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.ll_banner)
        LinearLayout llBanner;

        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(BannerBean.ResultBean resultBean) {
            Glide.with(mContext).load(resultBean.getCover()).into(ivBanner);
            tvTitle.setText(resultBean.getTitle());
            tvDesc.setText(resultBean.getDesc());
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fl_them)
        FrameLayout flThem;
        @BindView(R.id.fl_man)
        FrameLayout flMan;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_index)
        TextView tvIndex;
        @BindView(R.id.iv_them)
        ImageView ivThem;

        public HeadViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData() {
            flThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "番剧", Toast.LENGTH_SHORT).show();
                }
            });
            flMan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "国漫", Toast.LENGTH_SHORT).show();
                }
            });
            tvTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "时间表", Toast.LENGTH_SHORT).show();
                }
            });
            tvIndex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "索引", Toast.LENGTH_SHORT).show();
                }
            });
            ivThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "你还没有追过番剧", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class GuoManViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_more)
        LinearLayout llMore;
        @BindView(R.id.gv_guoman)
        GridView gvGuoman;
        public GuoManViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setData(List<ThemBean.ResultBean.PreviousBean.ListBean> list) {
            llMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "更多国漫", Toast.LENGTH_SHORT).show();
                }
            });
            GuoManAdapter adapter = new GuoManAdapter(mContext,list);
            gvGuoman.setAdapter(adapter);
        }
    }
}

package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.SearchBean;
import com.atguigu.biliapk.utlis.NumberUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/28.
 */

public class ZongHeAdapter extends RecyclerView.Adapter<ZongHeAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<SearchBean.DataBean.ItemsBean.ArchiveBean> archives;
    private final LayoutInflater inflater;


    public ZongHeAdapter(Context mContext, List<SearchBean.DataBean.ItemsBean.ArchiveBean> archives) {
        this.mContext = mContext;
        this.archives = archives;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.adapter_zonghe, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchBean.DataBean.ItemsBean.ArchiveBean archiveBean = archives.get(position);

        Glide.with(mContext)
                .load(archiveBean.getCover())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.itemImg);

        holder.itemTitle.setText(archiveBean.getTitle());
        holder.itemPlay.setText(NumberUtil.converString(archiveBean.getPlay()));
        holder.itemReview.setText(NumberUtil.converString(archiveBean.getDanmaku()));
        holder.itemUserName.setText(archiveBean.getAuthor());
        if (archiveBean.getDuration() != null) {
            holder.itemDuration.setText(archiveBean.getDuration());
        } else {
            holder.itemDuration.setText("--:--");
        }
    }

    @Override
    public int getItemCount() {
        return archives.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        ImageView itemImg;
        @BindView(R.id.item_duration)
        TextView itemDuration;
        @BindView(R.id.card_view)
        CardView cardView;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_user_name)
        TextView itemUserName;
        @BindView(R.id.item_play)
        TextView itemPlay;
        @BindView(R.id.item_review)
        TextView itemReview;
        @BindView(R.id.layout_play)
        LinearLayout layoutPlay;
        @BindView(R.id.item_view)
        RelativeLayout itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

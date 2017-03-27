package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.OriginalBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/26.
 */

public class OriginalAdapter extends RecyclerView.Adapter<OriginalAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<OriginalBean.DataBean> datas;
    private final LayoutInflater inflater;

    public OriginalAdapter(Context mContext, List<OriginalBean.DataBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_original,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            OriginalBean.DataBean dataBean = datas.get(position);
            holder.itemTitle.setText(dataBean.getTitle());
            holder.itemPlay.setText(dataBean.getPlay());
            holder.itemReview.setText(dataBean.getReply());
            holder.itemUserName.setText(dataBean.getName());
            holder.itemSortNum.setText(position + 1);
            setSortNumTextSize(holder, position);

            Glide.with(mContext)
                    .load(dataBean.getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.bili_default_image_tv)
                    .dontAnimate()
                    .into(holder.itemImg);

    }

    private void setSortNumTextSize(MyViewHolder holder, int position) {
        if (position == 0) {
            holder.itemSortNum.setTextSize(24);
            holder.itemSortNum.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        } else if (position == 1) {
            holder.itemSortNum.setTextSize(22);
            holder.itemSortNum.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        } else if (position == 2) {
            holder.itemSortNum.setTextSize(18);
            holder.itemSortNum.setTextColor(
                    mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            holder.itemSortNum.setTextSize(16);
            holder.itemSortNum.setTextColor(
                    mContext.getResources().getColor(R.color.black_alpha_30));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_sort_num)
        TextView itemSortNum;
        @BindView(R.id.item_img)
        ImageView itemImg;
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


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

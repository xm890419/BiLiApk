package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.TopicBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/23.
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<TopicBean.ListBean> datas;
    private final LayoutInflater inflater;


    public TopicAdapter(Context mContext, TopicBean topicBean) {
        this.mContext = mContext;
        this.datas = topicBean.getList();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.item_topic, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopicBean.ListBean listBean = datas.get(position);
        Glide.with(mContext).load(listBean.getCover()).into(holder.itemImage);
        holder.itemTitle.setText(listBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView itemImage;
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.card_view)
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

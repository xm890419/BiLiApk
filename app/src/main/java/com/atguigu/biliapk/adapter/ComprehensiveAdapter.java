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
import com.atguigu.biliapk.bean.RecommendBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ComprehensiveAdapter extends RecyclerView.Adapter<ComprehensiveAdapter.ViewHolder> {
    private final Context mContext;
    private final List<RecommendBean.DataBean> datas;
    private final LayoutInflater inflater;
    private ViewHolder viewHolder;

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public ComprehensiveAdapter(Context mContext, List<RecommendBean.DataBean> data) {
        this.mContext = mContext;
        this.datas = data;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_compre, null,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        viewHolder = holder;
        viewHolder.setData(datas,position);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_zong)
        ImageView ivZong;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_ming)
        TextView tvMing;
        @BindView(R.id.iv_compre)
        ImageView ivCompre;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void setData(List<RecommendBean.DataBean> datas, int position) {
            for (int i=0;i<datas.size();i++)
            Glide.with(mContext).load(datas.get(position).getCover()).into(viewHolder.ivZong);
            viewHolder.tvName.setText(datas.get(position).getTitle());
            viewHolder.tvMing.setText(datas.get(position).getTname());
            viewHolder.ivCompre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "弹对话框", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

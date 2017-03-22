package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.LiveBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class LifeAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<LiveBean.DataBean.PartitionsBean> datas;

    public LifeAdapter(Context mContext, List<LiveBean.DataBean.PartitionsBean> partitions) {
        this.mContext = mContext;
        this.datas = partitions;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_paint, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        List<LiveBean.DataBean.PartitionsBean.LivesBean> lives = datas.get(position).getLives();
        Glide.with(mContext).load(lives.get(5).getCover().getSrc()).into(viewHolder.ivRecommend);
        viewHolder.tvName.setText(lives.get(5).getTitle());
        viewHolder.tvMing.setText(lives.get(5).getOwner().getName());
        viewHolder.tvNumber.setText(lives.get(5).getOnline()+"");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_ming)
        TextView tvMing;
        @BindView(R.id.tv_number)
        TextView tvNumber;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

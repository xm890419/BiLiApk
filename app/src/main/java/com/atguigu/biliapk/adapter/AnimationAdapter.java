package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.AnimBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class AnimationAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<AnimBean.DataBean.BodyBean> datas;


    public AnimationAdapter(Context mContext, AnimBean.DataBean dataBean) {
        this.mContext = mContext;
        this.datas = dataBean.getBody();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.adapter_animation, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AnimBean.DataBean.BodyBean bodyBean = datas.get(position);
        viewHolder.tvDanmaku.setText(bodyBean.getDanmaku()+"");
        int online = Integer.parseInt(bodyBean.getParam());
        if (online >= 10000) {
            float number = online;
            viewHolder.tvView.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万");
        } else {
            viewHolder.tvView.setText(online + "");
        }
        viewHolder.tvName.setText(bodyBean.getTitle());
        Glide.with(mContext).load(bodyBean.getCover()).into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_view)
        TextView tvView;
        @BindView(R.id.tv_danmaku)
        TextView tvDanmaku;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

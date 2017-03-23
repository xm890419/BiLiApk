package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.ThemBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class PlayAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ThemBean.ResultBean.SerializingBean> datas;


    public PlayAdapter(Context mContext, List<ThemBean.ResultBean.SerializingBean> result) {
        this.mContext = mContext;
        this.datas = result;
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
            convertView = View.inflate(mContext, R.layout.item_play, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThemBean.ResultBean.SerializingBean serializingBean = datas.get(position);
        viewHolder.tvName.setText(serializingBean.getTitle());
        viewHolder.tvMing.setText("更新至第"+serializingBean.getNewest_ep_index()+"话");
        Glide.with(mContext).load(serializingBean.getCover()).into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_ming)
        TextView tvMing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

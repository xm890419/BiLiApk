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
 * Created by 熊猛 on 2017/3/23.
 */

public class GuoManAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ThemBean.ResultBean.PreviousBean.ListBean> datas;

    public GuoManAdapter(Context mContext, List<ThemBean.ResultBean.PreviousBean.ListBean> list) {
        this.mContext = mContext;
        this.datas = list;
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
            convertView = View.inflate(mContext, R.layout.item_guoman, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ThemBean.ResultBean.PreviousBean.ListBean listBean = datas.get(position);
        Glide.with(mContext).load(listBean.getCover()).into(viewHolder.ivRecommend);
        viewHolder.tvName.setText(listBean.getTitle());
        viewHolder.tvMing.setText("更新至第"+listBean.getNewest_ep_index()+"话");
        int online = Integer.parseInt(listBean.getFavourites());
        if (online >= 10000) {
            float number = online;
            viewHolder.textView.setText((float) (Math.round(number / 10000 * 10)) / 10 + "万人追剧");
        } else {
            viewHolder.textView.setText(online + "");
        }
        //viewHolder.textView.setText((Double.parseDouble(listBean.getFavourites())/1000)+"");
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_ming)
        TextView tvMing;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

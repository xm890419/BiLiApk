package com.atguigu.biliapk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.utlis.CartStorage;
import com.atguigu.biliapk.view.AddSubView;
import com.bumptech.glide.Glide;

import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/2/28.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private final Context mContext;
    private final List<RecommendBean.DataBean> allData;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox checkboxDeleteAll;


    public CartAdapter(Context mContext, List<RecommendBean.DataBean> allData, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox checkboxDeleteAll) {
        this.mContext = mContext;
        this.allData = allData;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.checkboxDeleteAll = checkboxDeleteAll;

        showTotalPrice();
    }

    public void showTotalPrice() {
        //显示总价格
        tvShopcartTotal.setText("合计"+getTotalPrice());
    }
    //返回总价格
    public double getTotalPrice() {
        double totalPrice = 0;
        if(allData != null && allData.size() >0) {
            for (int i =0;i < allData.size();i++){
                //遍历出来
                RecommendBean.DataBean goodsBean = allData.get(i);
                //必须是选择状态
                if(goodsBean.isChecked()) {
                    totalPrice = totalPrice + goodsBean.getDanmaku() * goodsBean.getNumber();
                }
            }
        }
        return totalPrice;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.item_shop_cart,null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //1.先得到数据
        final RecommendBean.DataBean goodsBean = allData.get(position);
        //2.绑定数据
        holder.cbGov.setChecked(goodsBean.isChecked());
        Glide.with(mContext).load(goodsBean.getCover()).into(holder.ivGov);
        //设置名称
        holder.tvDescGov.setText(goodsBean.getName());
        //设置价格
        holder.tvPriceGov.setText("￥"+goodsBean.getDanmaku());
        //设置数量
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        //设置库存-来自服务器-
        holder.addSubView.setMaxValue(100);

        holder.addSubView.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            //回传过来的值
            @Override
            public void onNumberChanger(int value) {
                //1.回调数量
                goodsBean.setNumber(value);
                //2.持久化
                CartStorage.getInstance(mContext).updateData(goodsBean);
                //3.显示总价格
                showTotalPrice();
            }
        });
        //设置发货
        holder.tvSeller.setText("由"+goodsBean.getSeller()+" 发货");

    }

    @Override
    public int getItemCount() {
        return allData.size();
    }
    //校验是否全选
    public void checkAll() {
        if(allData != null && allData.size() > 0) {
            int number = 0;
            for (int i =0;i < allData.size();i++){
                RecommendBean.DataBean goodsBean = allData.get(i);
                if(!goodsBean.isChecked()) {
                    //只要有一个不勾选
                    checkboxAll.setChecked(false);
                    checkboxDeleteAll.setChecked(false);
                }else {
                    //勾选
                    number++;
                }
            }
            //勾选的数量和购物车的条目相同就全选
            if(allData.size() == number) {
                checkboxAll.setChecked(true);
                checkboxDeleteAll.setChecked(true);
            }
        }else {
            //没有数据
            checkboxAll.setChecked(false);
            checkboxDeleteAll.setChecked(false);
        }
    }

    public void checkAll_none(boolean isChecked) {
        if(allData != null && allData.size() >0) {
            for (int i =0;i < allData.size();i++){
                //把购物车里面的Bean对象都设置勾选或者非勾选
                RecommendBean.DataBean goodsBean = allData.get(i);
                //设置是否勾选状态
                goodsBean.setChecked(isChecked);
                //设置CheckBox的状态
                checkboxAll.setChecked(isChecked);
                checkboxDeleteAll.setChecked(isChecked);
                //更新视图
                notifyItemChanged(i);
            }
        }
    }
    //删除数据
    public void deleteData() {
        /*if(allData != null && allData.size() > 0) {
            for (int i = 0;i < allData.size();i++){
                GoodsBean goodsBean = allData.get(i);
                //是否选中，选中才删除哦
                if(goodsBean.isChecked()) {
                    //1.内存中删除
                    allData.remove(goodsBean);
                    //2.本地也好保持
                    CartStorage.getInstance(mContext).deleteData(goodsBean);
                    //刷新数据
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }*/
        if(allData != null && allData.size() > 0) {
            for (Iterator iterator = allData.iterator();iterator.hasNext();){
                RecommendBean.DataBean goodsBean = (RecommendBean.DataBean) iterator.next();
                if(goodsBean.isChecked()) {
                    int position  = allData.indexOf(goodsBean);
                    //1.内存中删除
                    iterator.remove();
                    //本地也要同步
                    CartStorage.getInstance(mContext).deleteData(goodsBean);
                    //刷新页面
                    notifyItemRemoved(position);
                }
            }

        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_gov)
        CheckBox cbGov;
        @BindView(R.id.iv_gov)
        ImageView ivGov;
        @BindView(R.id.tv_desc_gov)
        TextView tvDescGov;
        @BindView(R.id.tv_price_gov)
        TextView tvPriceGov;
        @BindView(R.id.addSubView)
        AddSubView addSubView;
        @BindView(R.id.tv_seller)
        TextView tvSeller;

        MyViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //在这个地方设置item的点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回调接口
                    if(listener != null) {
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }
    }
    //设置item的监听
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //回调点击事件的监听
    private OnItemClickListener listener;

    //点击item的监听
    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }
}

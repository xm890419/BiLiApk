package com.atguigu.biliapk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.adapter.CartAdapter;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.utlis.CartStorage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopingActivity extends AppCompatActivity {

    @BindView(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.checkbox_all)
    CheckBox checkboxAll;
    @BindView(R.id.tv_shopcart_total)
    TextView tvShopcartTotal;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;
    @BindView(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @BindView(R.id.checkbox_delete_all)
    CheckBox checkboxDeleteAll;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_collection)
    Button btnCollection;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.iv_empty)
    TextView ivEmpty;
    @BindView(R.id.tv_empty_cart_tobuy)
    TextView tvEmptyCartTobuy;
    @BindView(R.id.ll_empty_shopcart)
    LinearLayout llEmptyShopcart;

    private List<RecommendBean.DataBean> allData;

    //1.设置两种状态
    //编辑状态
    private static final int ACTION_EDIT = 1;
    //完成状态
    private static final int ACTION_COMPLETE = 2;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        //2.设置编辑状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //显示去结算布局
        llCheckAll.setVisibility(View.VISIBLE);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到状态
                int action = (int) v.getTag();
                //2.根据不同状态做不同的处理
                if (action == ACTION_EDIT) {
                    //切换完成状态
                    showDelete();
                } else {
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });
    }
    private void hideDelete() {
        //1.设置编辑
        tvShopcartEdit.setTag(ACTION_EDIT);
        //2.隐藏删除控件
        llDelete.setVisibility(View.GONE);
        //3.显示结算控件
        llCheckAll.setVisibility(View.VISIBLE);
        //4.设置文本为-编辑
        tvShopcartEdit.setText("编辑");
        //5.把所有的数据设置勾选择状态
        if (adapter != null) {
            adapter.checkAll_none(true);
            adapter.checkAll();//校验是否全选
            adapter.showTotalPrice();//显示总价格
        }
    }

    private void showDelete() {
        //1.设置完成
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        //2.显示删除控件
        llDelete.setVisibility(View.VISIBLE);
        //3.隐藏结算控件
        llCheckAll.setVisibility(View.GONE);
        //4.设置文本为-完成
        tvShopcartEdit.setText("完成");
        //5.把所有的数据设置非选择状态
        if (adapter != null) {
            adapter.checkAll_none(false);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
    }
    public void initData() {
        showData();//显示数据
    }
    private void showData() {
        //得到数据
        allData = CartStorage.getInstance(this).getAllData();
        if (allData != null && allData.size() > 0) {
            //购物车有数据
            llEmptyShopcart.setVisibility(View.GONE);
            //设置RecyclerView的适配器
            adapter = new CartAdapter(this, allData, tvShopcartTotal, checkboxAll, checkboxDeleteAll);
            recyclerview.setAdapter(adapter);
            //布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            //设置点击事件
            adapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //1.设置Bean对象状态取反
                    RecommendBean.DataBean goodsBean = allData.get(position);
                    //状态取反
                    goodsBean.setChecked(!goodsBean.isChecked());
                    //刷新-重新绘制该条
                    adapter.notifyItemChanged(position);
                    //2.刷新价格
                    adapter.showTotalPrice();
                    //3.校验是否全选
                    adapter.checkAll();
                }
            });
            //3.校验是否全选
            adapter.checkAll();
        } else {
            //购物车没有数据
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out, R.id.checkbox_delete_all, R.id.btn_delete, R.id.btn_collection, R.id.tv_empty_cart_tobuy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shopcart_edit:
                Toast.makeText(this, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
                //Toast.makeText(mContext, "结算的全选/反选", Toast.LENGTH_SHORT).show();
                boolean isChecked = checkboxAll.isChecked();
                //全选和反全选
                adapter.checkAll_none(isChecked);
                //显示总价格
                adapter.showTotalPrice();

                break;
            case R.id.btn_check_out:
                //Toast.makeText(mContext, "结算", Toast.LENGTH_SHORT).show();
                //pay();
                /*Intent intent = new Intent(this,PayActivity.class);
                startActivity(intent);*/
                break;
            case R.id.checkbox_delete_all:
                //Toast.makeText(mContext, "删除的全选/反选", Toast.LENGTH_SHORT).show();
                isChecked = checkboxDeleteAll.isChecked();
                //全选和反全选
                adapter.checkAll_none(isChecked);
                //显示总价格
                adapter.showTotalPrice();

                break;
            case R.id.btn_delete:
                //Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
                adapter.deleteData();
                adapter.checkAll();
                showEmpty();

                break;
            case R.id.btn_collection:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_empty_cart_tobuy:
                //Toast.makeText(mContext, "去逛逛", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("checkedid",R.id.gv_zong);
                startActivity(intent);
                break;
        }
    }

    //没有数据的时候显示
    private void showEmpty() {
        if (adapter.getItemCount() == 0) {
            llEmptyShopcart.setVisibility(View.VISIBLE);
        }
    }
}

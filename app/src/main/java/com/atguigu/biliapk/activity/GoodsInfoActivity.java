package com.atguigu.biliapk.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.bean.RecommendBean;
import com.atguigu.biliapk.utlis.CartStorage;
import com.atguigu.biliapk.utlis.VirtualkeyboardHeight;
import com.atguigu.biliapk.view.AddSubView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @BindView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @BindView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @BindView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @BindView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @BindView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @BindView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @BindView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @BindView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @BindView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @BindView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @BindView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @BindView(R.id.tv_more_share)
    TextView tvMoreShare;
    @BindView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @BindView(R.id.tv_more_home)
    TextView tvMoreHome;
    @BindView(R.id.btn_more)
    Button btnMore;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.activity_goods_info)
    LinearLayout activityGoodsInfo;
    @BindView(R.id.tv_origin_price)
    TextView tvOriginPrice;
    @BindView(R.id.rl_origin_price)
    RelativeLayout rlOriginPrice;
    private String url;
    private RecommendBean.DataBean goodsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        goodsBean = (RecommendBean.DataBean) getIntent().getSerializableExtra("dataBean");
        String title = getIntent().getStringExtra("title");
        String cover = getIntent().getStringExtra("cover");
        String desc = getIntent().getStringExtra("desc");
        tvGoodInfoName.setText(title);
        tvGoodInfoPrice.setText("￥" + goodsBean.getDanmaku());
        Glide.with(this).load(cover).into(ivGoodInfoImage);
        tvGoodInfoDesc.setText(desc);
        loadWeb("http://www.bilibili.com/blackboard/activity-Hkyqb6nol.html");
    }

    private void setData() {
        /*//1.设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).placeholder(R.drawable.new_user_icon_background).into(ivGoodInfoImage);
        //2.设置名称和价格
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥" + goodsBean.getCover_price());*/

        //3.设置加载网页
        //loadWeb("http://www.bilibili.com/blackboard/activity-Hkyqb6nol.html");
    }

    private void loadWeb(String url) {
        WebSettings webSetting = wbGoodInfoMore.getSettings();
        //设置支持js
        webSetting.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSetting.setBuiltInZoomControls(true);
        //设置双击单击
        webSetting.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        wbGoodInfoMore.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wbGoodInfoMore.loadUrl(url);
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                //Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                if (llRoot.isShown()) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(this, "客服中心", Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(this,CallCenterActivity.class);
                startActivity(intent);*/
                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                //Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ShopingActivity.class);
                intent.putExtra("checkedid",R.id.rl_shop);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_good_info_addcart:
                //Toast.makeText(this, "添加到购物车", Toast.LENGTH_SHORT).show();
                //CartStorage.getInstance(this).addData(goodsBean);
                showPopwindow();
                break;
            case R.id.tv_more_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                /*intent = new Intent(this,ScanActivity.class);
                intent.putExtra(HomeAdapter.GOODS_BEAN,goodsBean.getFigure());
                startActivity(intent);*/
                break;
            case R.id.tv_more_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                //Toast.makeText(this, "主页", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.btn_more:
                //Toast.makeText(this, "消失更多页面", Toast.LENGTH_SHORT).show();
                llRoot.setVisibility(View.GONE);
                break;
        }
    }

    //缓存
    private RecommendBean.DataBean tempGoodsBean;
    //该商品信息是否在购物车中存在
    private boolean isExist = false;

    //显示popupWindow
    private void showPopwindow() {
        tempGoodsBean = CartStorage.getInstance(this).findDete(goodsBean.getTid());//购物车里面
        if (tempGoodsBean == null) {//是否在购物车中存在
            isExist = false;
            tempGoodsBean = goodsBean;
        } else {
            isExist = true;
        }
        // 1 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_add_product, null);

        // 2下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        // 3 参数设置
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
        window.setBackgroundDrawable(dw);
        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 4 控件处理
        ImageView iv_goodinfo_photo = (ImageView) view.findViewById(R.id.iv_goodinfo_photo);
        TextView tv_goodinfo_name = (TextView) view.findViewById(R.id.tv_goodinfo_name);
        TextView tv_goodinfo_price = (TextView) view.findViewById(R.id.tv_goodinfo_price);
        AddSubView nas_goodinfo_num = (AddSubView) view.findViewById(R.id.nas_goodinfo_num);
        Button bt_goodinfo_cancel = (Button) view.findViewById(R.id.bt_goodinfo_cancel);
        Button bt_goodinfo_confim = (Button) view.findViewById(R.id.bt_goodinfo_confim);

        // 加载图片
        Glide.with(GoodsInfoActivity.this).load(goodsBean.getCover()).into(iv_goodinfo_photo);
        // 名称
        tv_goodinfo_name.setText(tempGoodsBean.getTitle());
        // 显示价格
        tv_goodinfo_price.setText("￥"+tempGoodsBean.getDanmaku());

        // 设置最大值和当前值
        nas_goodinfo_num.setMaxValue(100);//库存100

        nas_goodinfo_num.setValue(tempGoodsBean.getNumber());
        nas_goodinfo_num.setOnNumberChangerListener(new AddSubView.OnNumberChangerListener() {
            @Override
            public void onNumberChanger(int value) {
                tempGoodsBean.setNumber(value);
            }
        });
        bt_goodinfo_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                if (isExist && tempGoodsBean.getNumber() == 1) {
                    tempGoodsBean.setNumber(tempGoodsBean.getNumber() + 1);
                }
            }
        });
        bt_goodinfo_confim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                //添加购物车
                CartStorage.getInstance(GoodsInfoActivity.this).addData(tempGoodsBean);
                Log.e("TAG", "66:" + tempGoodsBean.toString());
                Toast.makeText(GoodsInfoActivity.this, "添加购物车成功", Toast.LENGTH_SHORT).show();
            }
        });
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                window.dismiss();
            }
        });
        // 5 在底部显示
        window.showAtLocation(GoodsInfoActivity.this.findViewById(R.id.ll_goods_root),
                Gravity.BOTTOM, 0, VirtualkeyboardHeight.getBottomStatusHeight(GoodsInfoActivity.this));

    }

}

package com.atguigu.biliapk.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.base.BaseActivity;
import com.atguigu.biliapk.utlis.ConstantUtil;
import com.atguigu.biliapk.utlis.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_text_clear)
    ImageView searchTextClear;
    @BindView(R.id.search_img)
    ImageView searchImg;
    @BindView(R.id.search_card_view)
    CardView searchCardView;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //设置6.0以上StatusBar字体颜色
        StatusBarUtil.from(this)
                .setLightStatusBar(true)
                .process();
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_search;
    }
    @OnClick(R.id.search_back)
    public void onClick() {
        finish();
    }
    public static void launch(Activity activity, String str) {

        Intent mIntent = new Intent(activity, SearchActivity.class);
        mIntent.putExtra(ConstantUtil.EXTRA_CONTENT, str);
        activity.startActivity(mIntent);
    }
}

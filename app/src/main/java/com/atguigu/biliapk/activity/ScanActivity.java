package com.atguigu.biliapk.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.atguigu.biliapk.R;
import com.atguigu.biliapk.utlis.CreateQRImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.activity_scan)
    RelativeLayout activityScan;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        final String dataBean = getIntent().getStringExtra("dataBean");
        Glide.with(this).load(dataBean).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mBitmap = CreateQRImageUtils.createQRImage(dataBean, 800, 800, resource);
                ivScan.setImageBitmap(mBitmap);
            }
        });
    }
}

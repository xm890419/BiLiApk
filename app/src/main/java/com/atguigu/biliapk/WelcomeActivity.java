package com.atguigu.biliapk;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.atguigu.biliapk.activity.MainActivity;
import com.atguigu.biliapk.base.BaseActivity;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.activity_welcome)
    LinearLayout activityWelcome;
    @BindView(R.id.iv_welcome)
    ImageView ivWelcome;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

        showAnimation();
    }

    @Override
    public void initTitle() {

    }

    @Override
    public int getLayoutid() {
        return R.layout.activity_welcome;
    }
    private void showAnimation() {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //进入主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        activityWelcome.setAnimation(animation);
    }
}
